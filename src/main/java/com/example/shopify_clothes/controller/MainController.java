package com.example.shopify_clothes.controller;


import com.example.shopify_clothes.enumeration.Roles;
import com.example.shopify_clothes.model.*;
import com.example.shopify_clothes.service.BaskeService;
import com.example.shopify_clothes.service.CatlogServiceLayer;
import com.example.shopify_clothes.service.Credential;
import com.example.shopify_clothes.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class MainController {

    @Autowired
    Credential credential;
    @Autowired
    CatlogServiceLayer catlogServiceLayer;

    @Autowired
    OrderService orderService;

    @Autowired
    BaskeService baskeService;


    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String displayHome(Model model){

        List<Catlog> p = catlogServiceLayer.allCatlog();
        model.addAttribute("list",p);
        return "home.html";

    }

    @RequestMapping(value = "/category",method = RequestMethod.POST)
    public String displayCategory(@RequestParam("category")String category, Model model){
        log.info(category);
        List<Catlog> list = catlogServiceLayer.categoryLog(category);
        model.addAttribute("list",list);
        return "category.html";
    }

    @RequestMapping(value = "/product-detail/{catlogID}", method = RequestMethod.GET)
    public String displayProductDetail(@PathVariable("catlogID") long id, Model model) {

        Catlog list = catlogServiceLayer.findById(id);
        log.info(String.valueOf(list));

        model.addAttribute("p",list);


        return "productdetail.html";

    }

    @RequestMapping(value = "/product/add/", method = RequestMethod.POST)
    public String addtocart( @RequestParam("qty")int qty,@RequestParam("pid")Long catlogID){

        Basket checkbasket = baskeService.findbypid(catlogID);


        if(checkbasket == null){
            Basket basket = new Basket();
            basket.setQty(qty);
            basket.setCatlogs(catlogServiceLayer.findById(catlogID));
            baskeService.savebasket(basket);

        }else {
            int newQty = checkbasket.getQty() + qty;
            checkbasket.setQty(newQty);
            baskeService.savebasket(checkbasket);
        }
        return "redirect:/product-detail/"+catlogID;
    }



    @RequestMapping(value = "/cart")
    public String cart(Model model){

        // fetch all data from cart

        List<Basket> baskets = baskeService.getallBasket();

        List<ReturnCart> returnCarts = new ArrayList<>();

        float totalPrice = 0;

        for(Basket b: baskets){

            Float itemPrice = (float) (b.getCatlogs().getPrice() * b.getQty());
            totalPrice += itemPrice.doubleValue();
            ReturnCart r = new ReturnCart();
            r.setCart_id(b.getBasketId());
            r.setpID(b.getCatlogs().getCatlogID());
            r.setpName(b.getCatlogs().getCatlogName());
            r.setPrice(itemPrice);
            r.setQuantity(b.getQty());
            returnCarts.add(r);
        }

        model.addAttribute("list",returnCarts);
        model.addAttribute("tPrice",totalPrice);

        return "cart.html";
    }

    @PostMapping(value = "/cart-update/qty")
    public String updatecart( @RequestParam("qty") int qty, @RequestParam("basketID")Long basketID){
        Basket basket =  baskeService.findbybasketID(basketID).orElse(null);
        if(basket!=null){
            basket.setQty(qty);
            baskeService.savebasket(basket);
        }
        return "redirect:/cart";

    }

    @PostMapping(value = "/cart-update/delete")
    public String delete(@RequestParam("basketID")Long basketID){
        baskeService.delete(basketID);
        return "redirect:/cart";
    }


    @PostMapping("/cart/checkout")
    public String checkout(@Valid
            @RequestParam("first")String fName,
            @RequestParam("last")String lName,
            @RequestParam("email")String email,
            @RequestParam("add")String address,
            @RequestParam("tPrice")float price,
            @RequestParam("cardNumber")String number,
            @RequestParam("expiry")String expiry,
            @RequestParam("cvv")String cvv

    ){

        List<Basket> baskets = baskeService.getallBasket();

        List<Long> id = new ArrayList<>();


        for(Basket i: baskets){

            id.add(i.getCatlogs().getCatlogID());
        }


        Order order = new Order();
        order.settPrice(price);
        order.setOrderItems(id);
        order.setAddress(address);
        order.setCvv(cvv);
        order.setDate(LocalDate.now());
        order.setCardNumber(number);
        order.setExpiration(expiry);
        order.setEmail(email);
        order.setFirstName(fName);
        order.setLastName(lName);

        if(orderService.checkoutservice(order)){
            baskeService.deleteAll();

           for(Long i: id){
               Catlog e =  catlogServiceLayer.findById(i);
               int totalqty = e.getQty();
               int qtyordered = (int) (price/e.getPrice());
               totalqty -= qtyordered;
               e.setQty( totalqty );
               catlogServiceLayer.save(e);
           }
        };



        return "redirect:/cart";


    }

    @RequestMapping(value = "/admin/login",method = RequestMethod.GET)
    public String login(){
        return "login.html";
    }


    @RequestMapping(value = "/admin/login",method = RequestMethod.POST )
    public String login(@RequestParam("email")String username,@RequestParam("password")String pwd){

//        User admin = credential.login(username);

//        if(admin != null && Objects.equals(admin.getPwd(), pwd) && admin.getRole() == Roles.ADMIN){
//            return "redirect:/admin/panel";
//        }
        return "redirect:/admin/panel";
    }


    @RequestMapping(value = "/admin/panel",method = RequestMethod.GET)
    public String adminpanel(Model model){

        List<Order> orders = orderService.findAll();

        List<Catlog> catlogs = catlogServiceLayer.allCatlog();
        model.addAttribute("order",orders);
        model.addAttribute("items",catlogs);
        return "adminorders.html";

    }


    @PostMapping("/item/update")
    public String updateitem(
            @RequestParam("id")Long id,
            @RequestParam("price")Double price,
            @RequestParam("name")String name,
            @RequestParam("description")String description,
            @RequestParam("category")String category,
            @RequestParam("image")String image,
            @RequestParam("qty")int qty,
            @RequestParam("size")String size
    ){

        Catlog catlog = catlogServiceLayer.findById(id);

        if(catlog!=null){
            catlog.setCatlogName(name);
            catlog.setImage(image);
            catlog.setPrice(price);
            catlog.setQty(qty);
            catlog.setSize(size);
            catlog.setCatlogDescription(description);
            catlog.setCategory(category);

            catlogServiceLayer.save(catlog);
        }
        return "redirect:/admin/panel";
    }



    @PostMapping("/item/delete")
    public String del(@RequestParam("id")Long id){
        catlogServiceLayer.delete(id);
        return "redirect:/admin/panel";
    }



    @GetMapping("/admin-add")
    public String add(){
        return "adminadd.html";
    }

    @PostMapping("/admin-add")
    public String additem(
                           @RequestParam("productprice")Double price,
                           @RequestParam("productname")String name,
                           @RequestParam("description")String description,
                           @RequestParam("genre")String category,
                           @RequestParam("productimage")String image,
                           @RequestParam("productqty")int qty,
                           @RequestParam("productsize")String size){

        Catlog catlog = new Catlog();
        catlog.setQty(qty);
        catlog.setImage(image);
        catlog.setPrice(price);
        catlog.setCatlogName(name);
        catlog.setCatlogDescription(description);
        catlog.setCategory(category);
        catlog.setSize(size);
        catlogServiceLayer.save(catlog);


        return "redirect:/admin/panel";

    }










}
