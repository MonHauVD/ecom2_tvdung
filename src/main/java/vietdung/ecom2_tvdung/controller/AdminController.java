package vietdung.ecom2_tvdung.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vietdung.ecom2_tvdung.model.Address;
import vietdung.ecom2_tvdung.model.Customer;
import vietdung.ecom2_tvdung.model.DetailCustomer;
import vietdung.ecom2_tvdung.model.User;
import vietdung.ecom2_tvdung.service.CustomerDAOImpl;
import vietdung.ecom2_tvdung.service.ItemDAOImpl;
import vietdung.ecom2_tvdung.service.UserServiceImpl;

//import com.jtspringproject.JtSpringProject.models.Category;
//import com.jtspringproject.JtSpringProject.models.Product;
//import com.jtspringproject.JtSpringProject.models.User;
//import com.jtspringproject.JtSpringProject.services.categoryService;
//import com.jtspringproject.JtSpringProject.services.productService;
//import com.jtspringproject.JtSpringProject.services.userService;
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController
{

    private final UserServiceImpl userService;
    private final CustomerDAOImpl customerService;
//        private final ItemDAOImpl itemService;
//	private final categoryService categoryService;
//	private final productService productService;

    @Autowired
    public AdminController(UserServiceImpl _userService, ItemDAOImpl _itemService, CustomerDAOImpl _customerService)
    {
        this.userService = _userService;
//                this.itemService = _itemService;
        this.customerService = _customerService;
    }

    @GetMapping("/index")
    public String index(Model model)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        return "index";
    }

//	@GetMapping("login")
//	public ModelAndView adminlogin(@RequestParam(required = false) String error) {
//	    ModelAndView mv = new ModelAndView("adminlogin");
//	    if ("true".equals(error)) {
//	        mv.addObject("msg", "Invalid username or password. Please try again.");
//	    }
//	    return mv;
//	}
    @GetMapping("login")
    public String adminlogin()
    {
        return "thymeleaf/adminlogin";
    }

//	@GetMapping( value={"/","Dashboard"})
//	public ModelAndView adminHome(Model model) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    ModelAndView mv = new ModelAndView("adminHome");
//	    mv.addObject("admin", authentication.getName());
//	    return mv;
//	}
    @GetMapping(value =
    {
        "/", "Dashboard"
    })
    public String adminHome()
    {
        return "thymeleaf/adminHome";
    }

//	@GetMapping("categories")
//	public ModelAndView getcategory() {
//		ModelAndView mView = new ModelAndView("categories");
//		List<Category> categories = this.categoryService.getCategories();
//		mView.addObject("categories", categories);
//		return mView;
//	}
//	
//	@PostMapping("/categories")
//	public String addCategory(@RequestParam("categoryname") String category_name)
//	{
//		System.out.println(category_name);
//		
//		Category category =  this.categoryService.addCategory(category_name);
//		if(category.getName().equals(category_name)) {
//			return "redirect:categories";
//		}else {
//			return "redirect:categories";
//		}
//	}
//	
//	@GetMapping("categories/delete")
//	public String removeCategoryDb(@RequestParam("id") int id)
//	{	
//			this.categoryService.deleteCategory(id);
//			return "redirect:/admin/categories";
//	}
//	
//	@GetMapping("categories/update")
//	public String updateCategory(@RequestParam("categoryid") int id, @RequestParam("categoryname") String categoryname)
//	{
//		Category category = this.categoryService.updateCategory(id, categoryname);
//		return "redirect:/admin/categories";
//	}
//
//	
////	 --------------------------Remaining --------------------
//	@GetMapping("products")
//	public ModelAndView getproduct() {
//		ModelAndView mView = new ModelAndView("products");
//
//		List<Product> products = this.productService.getProducts();
//		
//		if (products.isEmpty()) {
//			mView.addObject("msg", "No products are available");
//		} else {
//			mView.addObject("products", products);
//		}
//		return mView;
//	}
//	
//	@GetMapping("products/add")
//	public ModelAndView addProduct() {
//		ModelAndView mView = new ModelAndView("productsAdd");
//		List<Category> categories = this.categoryService.getCategories();
//		mView.addObject("categories",categories);
//		return mView;
//	}
//
//	@RequestMapping(value = "products/add",method=RequestMethod.POST)
//	public String addProduct(@RequestParam("name") String name,@RequestParam("categoryid") int categoryId ,@RequestParam("price") int price,@RequestParam("weight") int weight, @RequestParam("quantity")int quantity,@RequestParam("description") String description,@RequestParam("productImage") String productImage) {
//		System.out.println(categoryId);
//		Category category = this.categoryService.getCategory(categoryId);
//		Product product = new Product();
//		product.setId(categoryId);
//		product.setName(name);
//		product.setCategory(category);
//		product.setDescription(description);
//		product.setPrice(price);
//		product.setImage(productImage);
//		product.setWeight(weight);
//		product.setQuantity(quantity);
//		this.productService.addProduct(product);
//		return "redirect:/admin/products";
//	}
//
//	@GetMapping("products/update/{id}")
//	public ModelAndView updateproduct(@PathVariable("id") int id) {
//		
//		ModelAndView mView = new ModelAndView("productsUpdate");
//		Product product = this.productService.getProduct(id);
//		List<Category> categories = this.categoryService.getCategories();
//
//		mView.addObject("categories",categories);
//		mView.addObject("product", product);
//		return mView;
//	}
//	
//	@RequestMapping(value = "products/update/{id}",method=RequestMethod.POST)
//	public String updateProduct(@PathVariable("id") int id ,@RequestParam("name") String name,@RequestParam("categoryid") int categoryId ,@RequestParam("price") int price,@RequestParam("weight") int weight, @RequestParam("quantity")int quantity,@RequestParam("description") String description,@RequestParam("productImage") String productImage)
//	{
//
////		this.productService.updateProduct();
//		return "redirect:/admin/products";
//	}
//	
//	@GetMapping("products/delete")
//	public String removeProduct(@RequestParam("id") int id)
//	{
//		this.productService.deleteProduct(id);
//		return "redirect:/admin/products";
//	}
//	
//	@PostMapping("products")
//	public String postproduct() {
//		return "redirect:/admin/categories";
//	}
    @GetMapping("customers")
    public ModelAndView getCustomerDetail()
    {
        ModelAndView mView = new ModelAndView("displayCustomers");
//        List<Customer> customers = this.customerService.findAll();
//        mView.addObject("customers", customers);
//        return mView;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_ecom2", "root", "12345678");

            String query
                    = "SELECT   c.id,  c.image,    u.first_name,    u.last_name,    u.email,    c.phone_number,    a.number,    a.street,    a.ward,    a.district,    a.province,    a.country\n"
                    + "FROM     customer c\n"
                    + "JOIN     user u ON c.user_id = u.id\n"
                    + "JOIN     address a ON c.address_id = a.id;";
            PreparedStatement stmt = con.prepareStatement(query);

            ResultSet rst = stmt.executeQuery();
            List<DetailCustomer> ls = new ArrayList<>();
            while (rst.next())
            {
                long cusid = rst.getInt(1);
                String Image = rst.getString(2);
                if (Image.length() == 0)
                {
                    Image = "../images/origin/person.png";
                }
                String firstname = rst.getString(3);
                String lastname = rst.getString(4);
                String email = rst.getString(5);
                String phonenumber = rst.getString(6);
                String numberHouse = rst.getString(7);
                String street = rst.getString(8);
                String ward = rst.getString(9);
                String district = rst.getString(10);
                String province = rst.getString(11);
                String country = rst.getString(12);
                String address = String.format("%s đường %s, phường %s, quận %s, tỉnh %s, nước %s", numberHouse, street, ward, district, province, country);
                DetailCustomer tmp = new DetailCustomer(cusid, Image, firstname, lastname, email, phonenumber, address);
                log.info("DetailCustomer " + tmp.toString());
                ls.add(tmp);
            }
            mView.addObject("detailCustomers", ls);
        } catch (Exception e)
        {
            log.warn("Exception:" + e);
            System.out.println("Exception:" + e);
        }
        return mView;
    }

    @GetMapping("/update_customer/{id}")
    public ModelAndView getUpdateCustomer(@PathVariable("id") String customerId)
    {
        ModelAndView mView = new ModelAndView("updateCustomer");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_ecom2", "root", "12345678");

            String query
                    = "SELECT   c.id,  c.image,    u.first_name,    u.last_name,    u.email,    c.phone_number,    a.number,    a.street,    a.ward,    a.district,    a.province,    a.country\n"
                    + "FROM     customer c\n"
                    + "JOIN     user u ON c.user_id = u.id\n"
                    + "JOIN     address a ON c.address_id = a.id\n"
                    + "where	c.id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, Long.parseLong(customerId));
            ResultSet rst = stmt.executeQuery();

            if (rst.next())
            {
                long cusid = rst.getInt(1);
                String Image = rst.getString(2);
                String firstname = rst.getString(3);
                String lastname = rst.getString(4);
                String email = rst.getString(5);
                String phonenumber = rst.getString(6);
                String numberHouse = rst.getString(7);
                String street = rst.getString(8);
                String ward = rst.getString(9);
                String district = rst.getString(10);
                String province = rst.getString(11);
                String country = rst.getString(12);
                DetailCustomer tmp = new DetailCustomer(cusid, Image, firstname, lastname, email, phonenumber, numberHouse, street, ward, district, province, country);
                mView.addObject("detailCustomer", tmp);
            }
        } catch (Exception e)
        {
            log.warn("Exception:" + e);
            System.out.println("Exception:" + e);
        }
        return mView;
    }

    @GetMapping("profileDisplay")
    public String profileDisplay(Model model)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_ecom2", "root", "12345678");

            String query
                    = "SELECT   c.id,  c.image,    u.first_name,    u.last_name,    u.email,    c.phone_number,    a.number,    a.street,    a.ward,    a.district,    a.province,    a.country\n"
                    + "FROM     customer c\n"
                    + "JOIN     user u ON c.user_id = u.id\n"
                    + "JOIN     address a ON c.address_id = a.id;";
            PreparedStatement stmt = con.prepareStatement(query);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            stmt.setString(1, username);

            ResultSet rst = stmt.executeQuery();

            if (rst.next())
            {
                long cusid = rst.getInt(1);
                String displayImage = rst.getString(2);
                String displayfirstname = rst.getString(3);
                String displaylastname = rst.getString(4);
                String displayemail = rst.getString(5);
                String displayphonenumber = rst.getString(6);
                String displaynumberHouse = rst.getString(7);
                String displaystreet = rst.getString(8);
                String displayward = rst.getString(9);
                String displaydistrict = rst.getString(10);
                String displayprovince = rst.getString(11);
                String displaycountry = rst.getString(12);
                String displayaddress = String.format("%s đường %s, phường %s, quận %s, tỉnh %s, nước %s", displaynumberHouse, displaystreet, displayward, displaydistrict, displayprovince, displaycountry);
//                model.addAttribute("userid", cusid);
//                model.addAttribute("image", displayImage);
//                model.addAttribute("firstName", displayfirstname);
//                model.addAttribute("lastName", displaylastname);
//                model.addAttribute("email", displayemail);
//                model.addAttribute("phoneNumber", displayphonenumber);
////			model.addAttribute("password",displaypassword);
//                model.addAttribute("address", displayaddress);
            }
        } catch (Exception e)
        {
            System.out.println("Exception:" + e);
        }
        System.out.println("Hello");
        return "updateProfile";
    }

    @RequestMapping(value = "/updating_customer/{id}", method = RequestMethod.POST)
    public String updateCustomerProfile(@RequestParam("id") int cusid, @ModelAttribute DetailCustomer detailCustomerNew)
    {
        detailCustomerNew.setCusId(cusid + 0L);
        log.info("Customer ID: " + cusid);
        log.info("Updated Details: " + detailCustomerNew);
        User updateUser = detailCustomerNew.getUser();
        Address updateAddress = detailCustomerNew.getAddress();
        Customer updateCustomer = detailCustomerNew.getCustomer();
        customerService.save(updateUser, updateAddress, updateCustomer);
        return "redirect:/admin/customers";
    }
}
