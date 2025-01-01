package vietdung.ecom2_tvdung.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import vietdung.ecom2_tvdung.controller.dto.DetailCustomerDto;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.service.BookDAOImpl;
import vietdung.ecom2_tvdung.service.CartDAOImpl;
import vietdung.ecom2_tvdung.service.ItemDAOImpl;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

@Controller
@Slf4j
public class MainController
{

    private final BookDAOImpl bookService;
    private final ItemDAOImpl itemService;
    private final UserServiceImpl userService;
    private final CartDAOImpl cartService;

    @Autowired
    public MainController(BookDAOImpl bookService, ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService)
    {
        this.bookService = bookService;
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/login")
    public String login()
    {
//            log.debug("Hello index");
//            log.info("Hello index");
//            log.trace("Hello index");
//            log.warn("Hello!!!");
        return "thymeleaf/login";
    }

    

    @GetMapping("/userloginvalidate")
    public String viewUserLoginPage()
    {
        return "/userloginvalidate";
    }

    @GetMapping("/")
    public ModelAndView home()
    {
//		return "thymeleaf/index"; //use html
//                return "index"; //use jsp file
        ModelAndView mView = new ModelAndView("index");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        List<ItemDto> itemDtos = itemService.getAllItemDtos();

        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        return mView;
    }
    
    @GetMapping("/catalog/book")
    public ModelAndView book()
    {
//		return "thymeleaf/index"; //use html
//                return "index"; //use jsp file
        ModelAndView mView = new ModelAndView("index");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        
        
        mView.addObject("bookButton", true);
        
        
        List<ItemDto> itemDtos = itemService.getAllItemDtosWithBook();

        
        
        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        return mView;
    }
    
    @GetMapping("/catalog/clothes")
    public ModelAndView clothes()
    {
//		return "thymeleaf/index"; //use html
//                return "index"; //use jsp file
        ModelAndView mView = new ModelAndView("index");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        
        
        mView.addObject("clothesButton", true);
        
        
        List<ItemDto> itemDtos = itemService.getAllItemDtosWithClothes();

        
        
        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        return mView;
    }
    
    @GetMapping("/catalog/electronics")
    public ModelAndView electronic()
    {
//		return "thymeleaf/index"; //use html
//                return "index"; //use jsp file
        ModelAndView mView = new ModelAndView("index");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        
        
        mView.addObject("electronicsButton", true);
        
        
        List<ItemDto> itemDtos = itemService.getAllItemDtosWithElectronic();

        
        
        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        return mView;
    }
    
    @GetMapping("/catalog/laptops")
    public ModelAndView laptop()
    {
//		return "thymeleaf/index"; //use html
//                return "index"; //use jsp file
        ModelAndView mView = new ModelAndView("index");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        
        
        mView.addObject("laptopButton", true);
        
        
        List<ItemDto> itemDtos = itemService.getAllItemDtosWithLaptop();

        
        
        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        return mView;
    }
    
    @GetMapping("/catalog/mobilephones")
    public ModelAndView mobilephone()
    {
//		return "thymeleaf/index"; //use html
//                return "index"; //use jsp file
        ModelAndView mView = new ModelAndView("index");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        
        
        mView.addObject("mobilephoneButton", true);
        
        
        List<ItemDto> itemDtos = itemService.getAllItemDtosWithMobilephone();

        
        
        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        return mView;
    }
    
    @GetMapping("/catalog/shoes")
    public ModelAndView shoes()
    {
//		return "thymeleaf/index"; //use html
//                return "index"; //use jsp file
        ModelAndView mView = new ModelAndView("index");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
        mView.addObject("currentUserId", UserId);
        
        
        mView.addObject("shoesButton", true);
        
        
        List<ItemDto> itemDtos = itemService.getAllItemDtosWithShoes();

        
        
        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        return mView;
    }
    
}
