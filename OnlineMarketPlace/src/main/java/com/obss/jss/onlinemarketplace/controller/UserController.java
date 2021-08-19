package com.obss.jss.onlinemarketplace.controller;

import com.obss.jss.onlinemarketplace.dto.MessageResponse;
import com.obss.jss.onlinemarketplace.service.BlackListService;
import com.obss.jss.onlinemarketplace.service.FavoriteListService;
import com.obss.jss.onlinemarketplace.service.ProductService;
import com.obss.jss.onlinemarketplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final BlackListService blackListService;

    private final FavoriteListService favoriteListService;

    private final ProductService productService;

    @Autowired
    public UserController(UserService userService, BlackListService blackListService, FavoriteListService favoriteListService, ProductService productService) {
        this.userService = userService;
        this.blackListService = blackListService;
        this.favoriteListService = favoriteListService;
        this.productService = productService;
    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                    @RequestParam(name = "name", required = false) String name,
                                    @RequestParam(name = "email", required = false) String email) {

        Pageable paged = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(userService.getUsers(name, email, paged));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestParam("id") String id) {

        return ResponseEntity.ok(userService.deleteUser(Long.parseLong(id)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateUser(@RequestParam("user") String userId,
                                        @RequestParam("username") String username,
                                        @RequestParam(name = "email") String email) {

        userService.updateUser(Long.parseLong(userId), username, email);
        return null;
    }

    @PutMapping("/favorite")
    public ResponseEntity<?> addFavorite(@RequestParam("user") String userId,
                                         @RequestParam("product") String productId,
                                         @RequestParam(name = "action", defaultValue = "add") String action
    ) {

        boolean result;
        if (action.equals("add")) {
            result = favoriteListService.addFavorite(Long.parseLong(userId), Long.parseLong(productId));
        } else {
            result = favoriteListService.removeFavorite(Long.parseLong(userId), Long.parseLong(productId));
        }


        if (result)
            return ResponseEntity.ok(new MessageResponse("Transaction successful"));
        else {
            return ResponseEntity.ok(new MessageResponse("Transaction failure"));
        }
    }

    @PutMapping("/blacklist")
    public ResponseEntity<?> updateBlackList(@RequestParam("user") String userId,
                                             @RequestParam("seller") String sellerId,
                                             @RequestParam(name = "action", defaultValue = "add") String action
    ) {

        boolean result;
        if (action.equals("add")) {
            result = blackListService.blackListed(Long.parseLong(userId), Long.parseLong(sellerId));
        } else {
            result = blackListService.removeFromBlackList(Long.parseLong(userId), Long.parseLong(sellerId));
        }


        if (result)
            return ResponseEntity.ok(new MessageResponse("Transaction successful"));
        else {
            return ResponseEntity.ok(new MessageResponse("Transaction failure"));
        }
    }
}
