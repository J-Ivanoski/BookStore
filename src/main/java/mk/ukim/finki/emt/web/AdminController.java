package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.model.enums.DeliveryStatus;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import mk.ukim.finki.emt.service.DeliveryServiceHelper;
import mk.ukim.finki.emt.service.QueryService;
import mk.ukim.finki.emt.service.StoreManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Riste Stojanov
 */
@Controller
public class AdminController {

  StoreManagementService storeManagementService;
  DeliveryServiceHelper dsh;
  QueryService queryService;
  Long i;

  @Autowired
  public AdminController(StoreManagementService storeManagementService, DeliveryServiceHelper deliveryServiceHelper, QueryService  queryService) {
    this.storeManagementService = storeManagementService;
    this.dsh = deliveryServiceHelper;
    this.queryService =  queryService;
    i=Long.parseLong("0");
  }

  @RequestMapping(value = {"/admin/category"}, method = RequestMethod.GET)
  public String addCategory(Model model) {
    model.addAttribute("pageFragment", "addCategory");
    return "index";
  }

  @RequestMapping(value = {"/admin/book"}, method = RequestMethod.GET)
  public String addProduct(Model model) {
    model.addAttribute("pageFragment", "addBook");
    return "index";
  }

  @RequestMapping(value = {"/admin/addDeliveryPackage"}, method = RequestMethod.GET)
  public String addDeliveryPackage(Model model) {
    model.addAttribute("pageFragment", "addDeliveryPackage");
    return "index";
  }

  @RequestMapping(value = {"/admin/deliveryPackage"}, method = RequestMethod.GET)
  public String showDeliveryPackage(Model model) {
    model.addAttribute("pageFragment", "showDeliveryPackage");
    model.addAttribute("deliveryPackages", queryService.findDeliveryPackages());
    return "index";
  }
/*
  @RequestMapping(value = {"/admin/deliveryPackage"}, method = RequestMethod.POST)
  public String createDeliveryPackage(Model model,
                               @RequestParam String status) {
    //Category category = storeManagementService.createTopLevelCategory(categoryName);
    return "redirect:/admin/deliveryPackage";
  }
*/

  @RequestMapping(value = {"/admin/category"}, method = RequestMethod.POST)
  public String createCategory(Model model,
                               @RequestParam String categoryName) {
    Category category = storeManagementService.createTopLevelCategory(categoryName);
    return "redirect:/admin/category";
  }

  @RequestMapping(value = {"/admin/book"}, method = RequestMethod.POST)
  public String createProduct(HttpServletRequest request,
                              HttpServletResponse resp,
                              Model model,
                              @RequestParam String name,
                              @RequestParam Long categoryId,
                              @RequestParam String authors,
                              @RequestParam String isbn,
                              @RequestParam Double price,
                              @RequestParam String description,
                              MultipartFile picture) throws IOException, SQLException {

    Book product = storeManagementService.createBook(
      name,
      categoryId,
      authors.split(";"),
      isbn,
      price
    );
    storeManagementService.addBookPicture(product.id, picture.getBytes(), picture.getContentType());

    model.addAttribute("product", product);
    return "index";
  }

  @RequestMapping(value = {"/admin/addDeliveryPackage"}, method = RequestMethod.POST)
  public String addDeliveryPackage(HttpServletRequest request,
                              HttpServletResponse resp,
                              Model model,
                              @RequestParam String status) throws IOException, SQLException {

    DeliveryPackage dp = dsh.createDeliveryPackage(i);
    DeliveryStatus s = DeliveryStatus.PENDING_PACKAGE_CREATION;
    for (DeliveryStatus temp : DeliveryStatus.values())
    {
      if (temp.toString().equals(status))
        s=temp;

    }
    dsh.updateDeliveryPackageStatus(dp.id, s);
    model.addAttribute("deliveryPackage", dp);
    return "redirect:/admin/addDeliveryPackage";
  }


}
