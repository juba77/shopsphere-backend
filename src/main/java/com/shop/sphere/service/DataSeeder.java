package com.shop.sphere.service;

import com.shop.sphere.dao.AdminRepository;
import com.shop.sphere.dao.ProductRepository;
import com.shop.sphere.models.Admin;
import com.shop.sphere.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
//@Profile("test")
public class DataSeeder {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initData(){

        Admin admin = new Admin();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("admin@shopsphere.com");
        admin.setPassword("@dmin123");

        adminRepository.save(admin);

        Product product1 = new Product();
        product1.setTitle("Elegant Stainless Steel Watch");
        product1.setDescription("This elegant stainless steel watch is the perfect companion for any occasion. Its timeless design and impeccable finish make it a must-have accessory for watch enthusiasts. .");
        product1.setPrice(149.99);
        product1.setQuantity(17);
        product1.setPhoto("https://media.istockphoto.com/id/1224750868/fr/vectoriel/illustration-r%C3%A9aliste-de-vecteur-de-montre-de-main-isol%C3%A9e-sur-le-fond-blanc.jpg?s=612x612&w=0&k=20&c=m28-HTG5K7RT8yz0KxGrfGx0oce8bCe9Uprsiz8AMgc=");

        Product product2 = new Product();
        product2.setTitle("Classic Leather Strap Watch");
        product2.setDescription("This classic leather strap watch combines style and functionality seamlessly. The genuine leather band ensures durability and comfort, while the precision movement keeps accurate time. Perfect for both casual and formal occasions.");
        product2.setPrice(124.99);
        product2.setQuantity(4);
        product2.setPhoto("https://media.istockphoto.com/id/628596336/fr/photo/montre-bracelet.jpg?s=612x612&w=0&k=20&c=YT7RGA89-PGmYmTDvW6fsndckj1LaFdCYkxoveit4yg=");

        Product product3 = new Product();
        product3.setTitle("Sporty Chronograph Watch");
        product3.setDescription("Stay on top of your game with this sporty chronograph watch. Its multifunctional design includes stopwatch and date features, making it ideal for athletes and active individuals. The rugged yet stylish look adds a touch of adventure to your wrist.");
        product3.setPrice(88.99);
        product3.setQuantity(84);
        product3.setPhoto("https://media.istockphoto.com/id/1137996297/fr/photo/montres-de-luxe-dhomme-dor-disolement-sur-le-rendu-3d-de-fond-blanc.jpg?s=612x612&w=0&k=20&c=wHbewcuteSbrYbPBH3QmnajNX-DE6Jzm_QxGDLoRSSE=");

        Product product4 = new Product();
        product4.setTitle("Luxurious Gold-plated Watch");
        product4.setDescription("Elevate your style with this luxurious gold-plated watch. The opulent design exudes sophistication, making it a statement piece for special events. The high-quality materials and craftsmanship ensure durability and elegance in one.");
        product4.setPrice(850.99);
        product4.setQuantity(8);
        product4.setPhoto("https://media.istockphoto.com/id/622032490/fr/photo/montre-bracelet.jpg?s=612x612&w=0&k=20&c=_sfpt8vlg1NDmGwLn7GdK2WRbBdklJE9EhkcqZGQfbY=");

        Product product5 = new Product();
        product5.setTitle("Sleek Stainless Steel Watch");
        product5.setDescription("Add a touch of modernity to your wrist with this sleek stainless steel watch. Its minimalist design and stainless steel bracelet create a contemporary look, perfect for everyday wear. The reliable movement ensures accurate timekeeping.");
        product5.setPrice(55.99);
        product5.setQuantity(45);
        product5.setPhoto("https://media.istockphoto.com/id/1387211820/fr/photo/montre-bracelet-dor%C3%A9e-avec-bracelet-en-cuir-marron-isol%C3%A9-sur-fond-blanc.jpg?s=612x612&w=0&k=20&c=46p7yjYQyqT6IQgncibUYMHoHKv7-CJmSn-3RZJ5kRQ=");

        Product product6 = new Product();
        product6.setTitle("Vintage-inspired Leather Watch");
        product6.setDescription("Embrace timeless charm with this vintage-inspired leather watch. The distressed leather strap and antique-style dial give it a classic appeal. Ideal for those who appreciate retro aesthetics and quality craftsmanship.");
        product6.setPrice(178.99);
        product6.setQuantity(2);
        product6.setPhoto("https://media.istockphoto.com/id/1141683841/fr/vectoriel/montre-en-or-isol%C3%A9-sur-fond-blanc-illustration-vectorielle.jpg?s=612x612&w=0&k=20&c=yoaPBP5kWUITDv_DZ_cvl89Kh2H05MJpvfiYo95TyNk=");

        Product product7 = new Product();
        product7.setTitle("Durable Titanium Watch");
        product7.setDescription("Designed for the adventurous souls, this durable titanium watch is built to withstand the toughest challenges. Its lightweight yet robust construction makes it suitable for outdoor activities. Stay on track wherever your adventures take you.");
        product7.setPrice(204.99);
        product7.setQuantity(12);
        product7.setPhoto("https://media.istockphoto.com/id/1086056236/fr/photo/lhomme-montre-bracelet-de-luxe-argent-u2019s-isol%C3%A9-sur-blanc-fond-3d.jpg?s=612x612&w=0&k=20&c=6NUblQPbXriy-4uJpDAfgCQqAHFYxGiSmwxgLc7564c=");

        Product product8 = new Product();
        product8.setTitle("Chic Rose Gold Watch");
        product8.setDescription("Make a statement with this chic rose gold watch. The feminine hue and elegant design add a touch of sophistication to any outfit. The comfortable bracelet and precise movement make it a practical and stylish choice.");
        product8.setPrice(75.99);
        product8.setQuantity(1);
        product8.setPhoto("https://media.istockphoto.com/id/1143956256/fr/photo/montre-bracelet.jpg?s=612x612&w=0&k=20&c=AsftYN5rNgnRUUcYXr5oOcvEW5APoHTwDAKf3-0Gw8s=");

        Product product9 = new Product();
        product9.setTitle("Fashionable Mesh Strap Watch");
        product9.setDescription("Stay in vogue with this fashionable mesh strap watch. The trendy mesh band and contemporary dial create a stylish accessory for fashion-forward individuals. Elevate your look with this eye-catching timepiece.");
        product9.setPrice(194.99);
        product9.setQuantity(7);
        product9.setPhoto("https://media.istockphoto.com/id/1345965015/fr/vectoriel/montre-bracelet-de-style-champ-avec-complication-jour-date.jpg?s=612x612&w=0&k=20&c=j6uHdCYQ4EejK_5lisiI2HNJeSOiaD-MprfZfbTWYws=");

        Product product10 = new Product();
        product10.setTitle("Rugged Military Style Watch");
        product10.setDescription("Channel your inner adventurer with this rugged military style watch. Its durable construction and camouflage-inspired design make it a favorite among outdoor enthusiasts. Stay on time and on trend with this utilitarian timepiece.");
        product10.setPrice(524.99);
        product10.setQuantity(0);
        product10.setPhoto("https://media.istockphoto.com/id/1469226641/fr/photo/montre-bracelet-analogique-classique-pour-hommes.jpg?s=612x612&w=0&k=20&c=75q6_wvJWtmiA3OUxm4o0sdU8fLj3QhTlZNxWfXMp7w=");

        Product product11 = new Product();
        product11.setTitle("Sophisticated Black Dial Watch");
        product11.setDescription("Achieve understated elegance with this sophisticated black dial watch. The black dial and minimalist markers exude refinement, making it suitable for formal occasions. Enhance your attire with this timeless and versatile watch.");
        product11.setPrice(99.99);
        product11.setQuantity(5);
        product11.setPhoto("https://media.istockphoto.com/id/1419381303/fr/vectoriel/mode-hommes-or-acier-inoxydable-montres-luxe-minimaliste-quartz-montre-bracelet-hommes.jpg?s=612x612&w=0&k=20&c=HC4lX3ZUN2ZL52BX9kSYUcQEK3PdreFOvSh94torEjo=");

        Product product12 = new Product();
        product12.setTitle("Smart Hybrid Fitness Watch");
        product12.setDescription("Stay fit and connected with this smart hybrid fitness watch. Track your workouts, monitor your health, and receive notifications, all in one device. The sleek design and advanced features make it an essential companion for the modern lifestyle.");
        product12.setPrice(24.99);
        product12.setQuantity(11);
        product12.setPhoto("https://media.istockphoto.com/id/522887163/fr/vectoriel/montres-vintage-or.jpg?s=612x612&w=0&k=20&c=aIPhCAK3HcNYCV9px8XVWkmThYVcXdRcV0CnNL_xFUI=");

        Product product13 = new Product();
        product13.setTitle("Bold Oversized Chronograph");
        product13.setDescription("Make a bold statement with this oversized chronograph watch. The large dial and detailed sub-dials create a striking visual impact. Perfect for individuals who appreciate bold and masculine timepieces.");
        product13.setPrice(449.99);
        product13.setQuantity(1);
        product13.setPhoto("https://media.istockphoto.com/id/640348630/fr/photo/montre-bracelet.jpg?s=612x612&w=0&k=20&c=jidILQyBDTvam479RPr6c74kJjGMAX7YK05CsyhiFOU=");

        Product product14 = new Product();
        product14.setTitle("Elegant Diamond-accented Watch");
        product14.setDescription("Indulge in luxury with this elegant diamond-accented watch. The sparkling diamonds on the bezel add a touch of glamour, making it a perfect choice for special occasions. Elevate your style with this exquisite and refined timepiece.");
        product14.setPrice(19.99);
        product14.setQuantity(19);
        product14.setPhoto("https://media.istockphoto.com/id/1359800374/fr/photo/montre-de-poignet-est-acier-inoxydable-sur-fond-blanc.jpg?s=612x612&w=0&k=20&c=r9f54OJ0pxGXRvNWllBLuBf76u-6EetXZQ7_QMvSkYY=");

        Product product15 = new Product();
        product15.setTitle("Classic Two-tone Bracelet Watch");
        product15.setDescription("Classic meets contemporary with this two-tone bracelet watch. The combination of stainless steel and gold accents offers a timeless look suitable for any occasion. Enhance your wrist with this versatile and sophisticated watch.");
        product15.setPrice(14.99);
        product15.setQuantity(49);
        product15.setPhoto("https://media.istockphoto.com/id/162419238/fr/photo/montre-chronographe.jpg?s=612x612&w=0&k=20&c=3MDKkPhgxMuqh_NyBa6qDoEZ6cAA5TG9K7wK0A2jMgA=");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);
        productRepository.save(product9);
        productRepository.save(product10);
        productRepository.save(product11);
        productRepository.save(product12);
        productRepository.save(product13);
        productRepository.save(product14);
        productRepository.save(product15);

    }

}
