package com.obss.jss.onlinemarketplace;

import com.obss.jss.onlinemarketplace.model.*;
import com.obss.jss.onlinemarketplace.repository.*;
import com.obss.jss.onlinemarketplace.service.FavoriteListService;
import com.obss.jss.onlinemarketplace.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Set;

@SpringBootTest
class OnlineMarketPlaceApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FavoriteListRepository favoriteListRepository;
    @Autowired
    private BlackListRepository blackListRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteListService favoriteListService;
    @Autowired
    private SellerRepository sellerRepository;

    @Test
    void contextLoads() {

        final Product product = Product.builder()
                .name("ürün")
                .description("açıklama")
                .inStock(10l)
                .price(100l)
                .build();
        final Product productSaved = productRepository.save(product);

        final Product product2 = Product.builder()
                .name("ürün2")
                .description("açıklama2")
                .inStock(10l)
                .price(100l)
                .build();
        final Product productSaved2 = productRepository.save(product2);

//        final User user = User.builder()
//                .username("ismail1")
//                .password("1234")
//                .email("ismail@hotmail.com")
//                .build();
//
//        final User user2 = User.builder()
//                .username("ismail")
//                .password("1234")
//                .email("ismail@hotmail.com")
//                .build();
//        final User userSaved2 = userService.createNewUser(user2);
//
//        final User userSaved = userService.createNewUser(user);

//        favoriteListService.addFavorite(userRepository.findByUsername("ismail1").getId(),
//                productRepository.findProductByName("ürün2").getId());

       // final FavoriteList favouriteList = new FavoriteList(user, new ArrayList<Product>());
//        favouriteList.getProductFavList().add(product);
//        favouriteList.getProductFavList().add(product2);
        final Seller seller = Seller.builder()
                .username("seller")
                .email("seller@seller.com")
                .productList(new ArrayList<Product>())
                .build();
        seller.getProductList().add(productSaved2);

        sellerRepository.save(seller);

       // final BlackList blackList = new BlackList(user, new ArrayList<User>());
        //blackList.setOwner(user);

       // user.setFavoriteList(favouriteList);
       // user.setBlackList(blackList);



//
//        user.getFavoriteList().getId();
//        //favoriteListRepository.delete(user.getFavoriteList());
//
//        //favoriteListRepository.save(user.getFavoriteList());
//
//        userRepository.findByUsername("ismail");
//
//        productRepository.findProductsByNameLike("ürün");
//
//        //userRepository.findUsersByRolesLike("ADMIN");
//
//      //  favouriteListRepository.findFavouriteListById(111l);

    }


}
