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
import vietdung.ecom2_tvdung.controller.dto.*;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.UserRepository;
import vietdung.ecom2_tvdung.service.*;

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

    private final CustomerDAOImpl customerService;
    private final BookDAOImpl bookService;
    private final ClothesDAOImpl clothesService;
    private final ElectronicDAOImpl electronicService;
    private final LaptopDAOImpl laptopService;
    private final MobilePhoneDAOImpl mobilePhoneService;
    private final ShoesDAOImpl shoesService;
    private final ItemDAOImpl itemService;
    private final UserServiceImpl userService;
    private final CartDAOImpl cartService;
    private final OrderDAOImpl orderService;
    private final ReviewDAOImpl reviewService;

    @Autowired
    public AdminController(CustomerDAOImpl customerService, BookDAOImpl bookService, ClothesDAOImpl clothesService, ElectronicDAOImpl electronicService, LaptopDAOImpl laptopService, MobilePhoneDAOImpl mobilePhoneService, ShoesDAOImpl shoesService, ItemDAOImpl itemService, UserServiceImpl userService, CartDAOImpl cartService, OrderDAOImpl orderService, ReviewDAOImpl reviewService)
    {
        this.customerService = customerService;
        this.bookService = bookService;
        this.clothesService = clothesService;
        this.electronicService = electronicService;
        this.laptopService = laptopService;
        this.mobilePhoneService = mobilePhoneService;
        this.shoesService = shoesService;
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.reviewService = reviewService;
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
    
//    @PostMapping("/logout")
//    public String logout() {
//        return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
//    }

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
            List<DetailCustomerDto> ls = new ArrayList<>();
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
                DetailCustomerDto tmp = new DetailCustomerDto(cusid, Image, firstname, lastname, email, phonenumber, address);
                log.info("DetailCustomer " + tmp.toString());
                ls.add(tmp);
            }
            if (ls.isEmpty())
                    mView.addObject("msg", "No Customer are available");
            else
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
                DetailCustomerDto tmp = new DetailCustomerDto(cusid, Image, firstname, lastname, email, phonenumber, numberHouse, street, ward, district, province, country);
                mView.addObject("detailCustomer", tmp);
            }
        } catch (Exception e)
        {
            log.warn("Exception:" + e);
            System.out.println("Exception:" + e);
        }
        return mView;
    }

    

    @RequestMapping(value = "/updating_customer/{id}", method = RequestMethod.POST)
    public String updateCustomerProfile(@RequestParam("id") int cusid, @ModelAttribute DetailCustomerDto detailCustomerNew)
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
    
    
    @GetMapping("/delete_customer/{id}")
    public String deleteCustomer(@PathVariable("id") String customerId)
    {
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_ecom2", "root", "12345678");
            Long user_id = userService.getUserIdByCustomerID(Long.parseLong(customerId));            
            String query1
                    = "DELETE\n"
                    + "FROM     user u\n"
                    + "where	u.id = ?";
            PreparedStatement stmt1 = con.prepareStatement(query1);
            stmt1.setLong(1, user_id);
            stmt1.executeUpdate();
            
            String query2
                    = "DELETE\n"
                    + "FROM     customer c\n"
                    + "where	c.id = ?";
            PreparedStatement stmt2 = con.prepareStatement(query2);
            stmt2.setLong(1, Long.parseLong(customerId));
            stmt2.executeUpdate();

        } catch (Exception e)
        {
            log.warn("Exception:" + e);
            System.out.println("Exception:" + e);
        }
        return "redirect:/admin/customers";
    }
    
    //chua su dung den phan nay
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
//----------------------------------Book-----------------------------------------------
    @GetMapping("books")
    public ModelAndView getBook() {
            ModelAndView mView = new ModelAndView("books");

            List<DetailBookDto> books = this.bookService.getAllDetailBookDto();

            if (books.isEmpty()) {
                    mView.addObject("msg", "No books are available");
            } else {
                    mView.addObject("books", books);
            }
            return mView;
    }
	
	@GetMapping("/books/add")
	public ModelAndView addBook() {
		ModelAndView mView = new ModelAndView("addBook");
		return mView;
	}

	@RequestMapping(value = "/books/adding",method=RequestMethod.POST)
	public String addingBook (@ModelAttribute DetailBookDto detailBookDto) {
            log.info("Add book: " + detailBookDto);
            bookService.addNewBook(detailBookDto);
            return "redirect:/admin/books";
	}

	@GetMapping("books/update_book/{id}")
	public ModelAndView updateproduct(@PathVariable("id") Long id) {
		
		ModelAndView mView = new ModelAndView("updateBook");
                DetailBookDto detailBookDto = bookService.getDetailBookDtoByBookId(id);		
		mView.addObject("detailBook", detailBookDto);
		return mView;
	}
	
	@RequestMapping(value = "books/updating_book/{id}",method=RequestMethod.POST)
	public String updateProduct(@ModelAttribute DetailBookDto detailBookDto)
	{
            log.info("Update book: " + detailBookDto);
            bookService.updateBook(detailBookDto);
            return "redirect:/admin/books";
	}
	
	@GetMapping("books/delete_book/{id}")
	public String removeProduct(@PathVariable("id") Long id)
	{
		bookService.deleteBook(id);
		return "redirect:/admin/books";
	}

        
//----------------------------------Clothes-----------------------------------------------
    @GetMapping("clothes")
    public ModelAndView getClothes() {
            ModelAndView mView = new ModelAndView("clothes");

            List<DetailClothesDto> clothes = this.clothesService.getAllDetailClothesDto();

            if (clothes.isEmpty()) {
                    mView.addObject("msg", "No clothes are available");
            } else {
                    mView.addObject("clothes", clothes);
            }
            return mView;
    }
	
	@GetMapping("/clothes/add")
	public ModelAndView addClothes() {
		ModelAndView mView = new ModelAndView("addClothes");
		return mView;
	}

	@RequestMapping(value = "/clothes/adding",method=RequestMethod.POST)
	public String addingClothes (@ModelAttribute DetailClothesDto detailClothesDto) {
            log.info("Add clothes: " + detailClothesDto);
            clothesService.addNewClothes(detailClothesDto);
            return "redirect:/admin/clothes";
	}

	@GetMapping("clothes/update_clothes/{id}")
	public ModelAndView updateClothes(@PathVariable("id") Long id) {
		
		ModelAndView mView = new ModelAndView("updateClothes");
                DetailClothesDto detailClothesDto = clothesService.getDetailClothesDtoByClothesId(id);		
		mView.addObject("detailClothes", detailClothesDto);
		return mView;
	}
	
	@RequestMapping(value = "clothes/updating_clothes/{id}",method=RequestMethod.POST)
	public String updateClothes(@ModelAttribute DetailClothesDto detailClothesDto)
	{
            log.info("Update clothes: " + detailClothesDto);
            clothesService.updateClothes(detailClothesDto);
            return "redirect:/admin/clothes";
	}
	
	@GetMapping("clothes/delete_clothes/{id}")
	public String removeClothes(@PathVariable("id") Long id)
	{
		clothesService.deleteClothes(id);
		return "redirect:/admin/clothes";
	}
//----------------------------------Electronic-----------------------------------------------
    @GetMapping("electronic")
    public ModelAndView getElectronic() {
            ModelAndView mView = new ModelAndView("electronic");

            List<DetailElectronicDto> electronic = this.electronicService.getAllDetailElectronicDto();

            if (electronic.isEmpty()) {
                    mView.addObject("msg", "No electronic are available");
            } else {
                    mView.addObject("electronic", electronic);
            }
            return mView;
    }
	
	@GetMapping("/electronic/add")
	public ModelAndView addElectronic() {
		ModelAndView mView = new ModelAndView("addElectronic");
		return mView;
	}

	@RequestMapping(value = "/electronic/adding",method=RequestMethod.POST)
	public String addingElectronic (@ModelAttribute DetailElectronicDto detailElectronicDto) {
            log.info("Add electronic: " + detailElectronicDto);
            electronicService.addNewElectronic(detailElectronicDto);
            return "redirect:/admin/electronic";
	}

	@GetMapping("electronic/update_electronic/{id}")
	public ModelAndView updateElectronic(@PathVariable("id") Long id) {
		
		ModelAndView mView = new ModelAndView("updateElectronic");
                DetailElectronicDto detailElectronicDto = electronicService.getDetailElectronicDtoByElectronicId(id);		
		mView.addObject("detailElectronic", detailElectronicDto);
		return mView;
	}
	
	@RequestMapping(value = "electronic/updating_electronic/{id}",method=RequestMethod.POST)
	public String updateElectronic(@ModelAttribute DetailElectronicDto detailElectronicDto)
	{
            log.info("Update electronic: " + detailElectronicDto);
            electronicService.updateElectronic(detailElectronicDto);
            return "redirect:/admin/electronic";
	}
	
	@GetMapping("electronic/delete_electronic/{id}")
	public String removeElectronic(@PathVariable("id") Long id)
	{
		electronicService.deleteElectronic(id);
		return "redirect:/admin/electronic";
	}
        
//----------------------------------Laptop-----------------------------------------------
    @GetMapping("laptop")
    public ModelAndView getLaptop() {
            ModelAndView mView = new ModelAndView("laptop");

            List<DetailLaptopDto> laptop = this.laptopService.getAllDetailLaptopDto();

            if (laptop.isEmpty()) {
                    mView.addObject("msg", "No laptop are available");
            } else {
                    mView.addObject("laptop", laptop);
            }
            return mView;
    }
	
	@GetMapping("/laptop/add")
	public ModelAndView addLaptop() {
		ModelAndView mView = new ModelAndView("addLaptop");
		return mView;
	}

	@RequestMapping(value = "/laptop/adding",method=RequestMethod.POST)
	public String addingLaptop (@ModelAttribute DetailLaptopDto detailLaptopDto) {
            log.info("Add laptop: " + detailLaptopDto);
            laptopService.addNewLaptop(detailLaptopDto);
            return "redirect:/admin/laptop";
	}

	@GetMapping("laptop/update_laptop/{id}")
	public ModelAndView updateLaptop(@PathVariable("id") Long id) {
		
		ModelAndView mView = new ModelAndView("updateLaptop");
                DetailLaptopDto detailLaptopDto = laptopService.getDetailLaptopDtoByLaptopId(id);		
		mView.addObject("detailLaptop", detailLaptopDto);
		return mView;
	}
	
	@RequestMapping(value = "laptop/updating_laptop/{id}",method=RequestMethod.POST)
	public String updateLaptop(@ModelAttribute DetailLaptopDto detailLaptopDto)
	{
            log.info("Update laptop: " + detailLaptopDto);
            laptopService.updateLaptop(detailLaptopDto);
            return "redirect:/admin/laptop";
	}
	
	@GetMapping("laptop/delete_laptop/{id}")
	public String removeLaptop(@PathVariable("id") Long id)
	{
		laptopService.deleteLaptop(id);
		return "redirect:/admin/laptop";
	}
        
 //----------------------------------MobilePhone-----------------------------------------------
    @GetMapping("mobilePhone")
    public ModelAndView getMobilePhone() {
            ModelAndView mView = new ModelAndView("mobilePhone");

            List<DetailMobilePhoneDto> mobilePhone = this.mobilePhoneService.getAllDetailMobilePhoneDto();

            if (mobilePhone.isEmpty()) {
                    mView.addObject("msg", "No mobilePhone are available");
            } else {
                    mView.addObject("mobilePhone", mobilePhone);
            }
            return mView;
    }
	
	@GetMapping("/mobilePhone/add")
	public ModelAndView addMobilePhone() {
		ModelAndView mView = new ModelAndView("addMobilePhone");
		return mView;
	}

	@RequestMapping(value = "/mobilePhone/adding",method=RequestMethod.POST)
	public String addingMobilePhone (@ModelAttribute DetailMobilePhoneDto detailMobilePhoneDto) {
            log.info("Add mobilePhone: " + detailMobilePhoneDto);
            mobilePhoneService.addNewMobilePhone(detailMobilePhoneDto);
            return "redirect:/admin/mobilePhone";
	}

	@GetMapping("mobilePhone/update_mobilePhone/{id}")
	public ModelAndView updateMobilePhone(@PathVariable("id") Long id) {
		
		ModelAndView mView = new ModelAndView("updateMobilePhone");
                DetailMobilePhoneDto detailMobilePhoneDto = mobilePhoneService.getDetailMobilePhoneDtoByMobilePhoneId(id);		
		mView.addObject("detailMobilePhone", detailMobilePhoneDto);
		return mView;
	}
	
	@RequestMapping(value = "mobilePhone/updating_mobilePhone/{id}",method=RequestMethod.POST)
	public String updateMobilePhone(@ModelAttribute DetailMobilePhoneDto detailMobilePhoneDto)
	{
            log.info("Update mobilePhone: " + detailMobilePhoneDto);
            mobilePhoneService.updateMobilePhone(detailMobilePhoneDto);
            return "redirect:/admin/mobilePhone";
	}
	
	@GetMapping("mobilePhone/delete_mobilePhone/{id}")
	public String removeMobilePhone(@PathVariable("id") Long id)
	{
		mobilePhoneService.deleteMobilePhone(id);
		return "redirect:/admin/mobilePhone";
	}   
        
//----------------------------------Shoes-----------------------------------------------
    @GetMapping("shoes")
    public ModelAndView getShoes() {
            ModelAndView mView = new ModelAndView("shoes");

            List<DetailShoesDto> shoes = this.shoesService.getAllDetailShoesDto();

            if (shoes.isEmpty()) {
                    mView.addObject("msg", "No shoes are available");
            } else {
                    mView.addObject("shoes", shoes);
            }
            return mView;
    }
	
	@GetMapping("/shoes/add")
	public ModelAndView addShoes() {
		ModelAndView mView = new ModelAndView("addShoes");
		return mView;
	}

	@RequestMapping(value = "/shoes/adding",method=RequestMethod.POST)
	public String addingShoes (@ModelAttribute DetailShoesDto detailShoesDto) {
            log.info("Add shoes: " + detailShoesDto);
            shoesService.addNewShoes(detailShoesDto);
            return "redirect:/admin/shoes";
	}

	@GetMapping("shoes/update_shoes/{id}")
	public ModelAndView updateShoes(@PathVariable("id") Long id) {
		
		ModelAndView mView = new ModelAndView("updateShoes");
                DetailShoesDto detailShoesDto = shoesService.getDetailShoesDtoByShoesId(id);		
		mView.addObject("detailShoes", detailShoesDto);
		return mView;
	}
	
	@RequestMapping(value = "shoes/updating_shoes/{id}",method=RequestMethod.POST)
	public String updateShoes(@ModelAttribute DetailShoesDto detailShoesDto)
	{
            log.info("Update shoes: " + detailShoesDto);
            shoesService.updateShoes(detailShoesDto);
            return "redirect:/admin/shoes";
	}
	
	@GetMapping("shoes/delete_shoes/{id}")
	public String removeShoes(@PathVariable("id") Long id)
	{
		shoesService.deleteShoes(id);
		return "redirect:/admin/shoes";
	}        
        
        
//        -------------Order-------------------
        @GetMapping("/order")
    public ModelAndView orderList()
    {
        ModelAndView mView = new ModelAndView("orderListForAdmin");
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
//        mView.addObject("currentUserId", UserId);
        
        WrapperOrderListDto wrapper = orderService.getAllWrapperOrderListDto();
        
        mView.addObject("orders", wrapper);
        
        return mView;
    }

    
    @GetMapping(value = "/processed_order/{orderId}")
    public String confirmProcessedOrderByOrderId(@PathVariable("orderId") Long orderId)
    {
        orderService.setProcessedOrderByOrderId(orderId);
        return "redirect:/admin/order";
    }
    
    @GetMapping(value = "/paid_order/{orderId}")
    public String confirmPaidOrderByOrderId(@PathVariable("orderId") Long orderId)
    {
        orderService.setPaidOrderByOrderId(orderId);
        return "redirect:/admin/order";
    }
    
    @GetMapping(value = "/admin/detail_order/{orderId}")
    public String getDetailedOrderByOrderId(@PathVariable("orderId") Long orderId)
    {
        
        return "redirect:/admin/detail_order/" + orderId;
    }
    
 // ------------------- Detail_order-------------------------------
    @GetMapping("/detail_order/{orderID}")
    public ModelAndView orderList(@PathVariable("orderID") Long orderID)
    {
        ModelAndView mView = new ModelAndView("orderDetail");
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(email);
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        Long UserId = currentUser.getId();
        mView.addObject("username", name);
//        mView.addObject("currentUserId", UserId);
        //Can check them order nay co thuoc nguoi dung nay không?
        //Se bo sung sau!!!!
        OrderDto orderDto = orderService.getOrderDtoByOrderId(orderID);
        if(orderDto != null)
        {
            mView.addObject("order", orderDto);
            if((orderDto.getState().compareTo(OrderState.Received) != 0) && (orderDto.getState().compareTo(OrderState.Completed) != 0))
            {
                mView.addObject("isNotReceived", true);
            }
        }
        
        if(currentUser.getRole().compareTo(Role.ADMIN) == 0)
        {
            mView.addObject("isNotReceived", true);
        }
        
        List<ItemDto> itemDtos = cartService.getListItemDtoByOrderId(orderID);

        if (itemDtos.isEmpty())
        {
            mView.addObject("msg", "No products are available");
        } else
        {
            mView.addObject("items", itemDtos);
        }
        
        return mView;
    }

    
    @GetMapping(value = "/detail_order/review/{orderId}/{itemId}")
    public String reviewItemByOrderIdAndItemId(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId)
    {
        
        return "redirect:/admin/detail_order/";
    }
    
    @GetMapping(value = "/detail_order/detail_item/{itemId}")
    public String getDetailedItemByItemId(@PathVariable("itemId") Long itemId)
    {
        
        return "redirect:/admin/detail_item/" + itemId;
    }
  
//-------------------------------------Item detail--------------------------
    @GetMapping("/detail_item/{itemId}")
    public ModelAndView detail(@PathVariable("itemId") Long itemId)
    {
        ModelAndView mView = new ModelAndView("itemDetail");
        
        ItemDetailDto itemDetailDtos = itemService.getItemDetailDtoByItemId(itemId);
        mView.addObject("item", itemDetailDtos);
        
        List<ReviewDto> lsReviewDto = reviewService.getListReviewDtoByItemId(itemId);
        
        mView.addObject("reviews",  lsReviewDto);
        
        return mView;
    }
}
