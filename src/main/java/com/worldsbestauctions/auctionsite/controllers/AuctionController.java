package com.worldsbestauctions.auctionsite.controllers;


import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.entities.Images;
import com.worldsbestauctions.auctionsite.services.AuctionService;
import com.worldsbestauctions.auctionsite.services.HibernateSearchService;
import com.worldsbestauctions.auctionsite.services.ImageService;
import com.worldsbestauctions.auctionsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    AuctionService auctionPostsService;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    @Autowired
    private HibernateSearchService searchservice;

   @GetMapping
    Iterable<Auctions> getTopTen(){
        return auctionPostsService.getTopTen();
    }

   /*@GetMapping
   Iterable<Auctions> getActivePosts(Auctions a) {
        return auctionPostsService.getActivePosts(a.getEndtime());
    }*/

    /*@GetMapping
    Iterable<Auctions> getAll(){
        return auctionPostsService.getAll();
    }*/

    @PostMapping
    long createNewAuction(@RequestBody Auctions body, HttpServletRequest request){
        body.setAuctionowner(userService.getUserByEmail(request.getUserPrincipal().getName()).getUserid());
        body.setStarttime(LocalDateTime.now());
        body.setEndtime(LocalDateTime.now().plusDays(2));
        long id = auctionPostsService.save(body).getAuctionid();
        for(String path : body.getImages()){
            imageService.save(new Images(id, path));
        }
        return id;
    }

    public String search(@RequestParam(value = "search", required = false) String q, Model model) {
        List<Auctions> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearch(q);

        } catch (Exception ex) {
            // here you should handle unexpected errors
            // ...
            // throw ex;
        }
        model.addAttribute("search", searchResults);
        return "index";
    }

}
