package web.controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.UserServiceImpl;
import java.util.List;
@RestController
@RequestMapping("/users/")
public class UserController
{
   @Autowired
   private UserServiceImpl userService;

   public UserController(UserServiceImpl userService1)
   {
      this.userService = userService1;
   }

   @GetMapping("/{id}/")
   public String get(@PathVariable Long userId, ModelMap model)
   {
      User user = userService.getUserById(userId);
      model.addAttribute("user", user);
      return "user";
   }

   @PostMapping
   public String add(@ModelAttribute("user") User user)
   {
      userService.addUser(user);
      return "redirect:/users";
   }

   @DeleteMapping("/{id}/")
   public String delete(@PathVariable Long userId)
   {
      User user = userService.getUserById(userId);
      if(user != null)
      {
         userService.deleteUser(userId);
      }
      return "redirect:/users";
   }

   @PatchMapping("/{id}/")
   public String update(@ModelAttribute("user") User newUser)
   {

      userService.updateUser(newUser);
      return "redirect:/users";
   }
   @GetMapping
   public String listAllUsers(ModelMap model)
   {
      List<User> userList = userService.getAllUsers();
      model.addAttribute("users", userList);
      return "users";
   }
}
