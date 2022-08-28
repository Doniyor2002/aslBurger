package com.example.botservice.component;

import com.example.botservice.entity.Role;
import com.example.botservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Dataloader implements CommandLineRunner {
    private final AddressRepository addressRepository;
    private final AttachmnetRepository attachmentRepository;
    private final CategoryRepository categoryRepository;
    private final DetailRepository detailRepository;
    private final DiscountRepository discountRepository;
    private final FavouriteRepository favouriteRepository;
    private final FilialRepository filialRepository;
    private final NotificationRepository notificationRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final ProductRepository productRepository;
    private final RoleRepository roleRepository;
    private final SaleRepository saleRepository;
    private final SupportRepository supportRepository;
    private final UserRepository userRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")) {
            Role role=new Role();
            role.setNameRu("АДМИН");
            role.setNameUz("ADMIN");
            Role role1=new Role();
            role.setNameRu("ПОЛЬЗОВАТЕЛЬ");
            role.setNameUz("USER");
            Role role2=new Role();
            role.setNameRu("ОПЕРАТОР");
            role.setNameUz("OPERATOR");
            Role role3=new Role();
            role.setNameRu("ДИСПЕЧЕР");
            role.setNameUz("DISPECHER");
            Role role4=new Role();
            role.setNameRu("Курьер");
            role.setNameUz("Kuryer");
            roleRepository.save(role);
            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);
            roleRepository.save(role4);

//            Address qattadir = addressRepository.save(new Address(1l,"Qattadir","где-то",123D, 321D, "Qattadir"));
//            Address qatdir = addressRepository.save(new Address(2l,"Qatdir","где-то",12345D, 54321D, "Qatdir"));


            //qolganlari xato berdi shunga commentga olib qo`ydim

//            Date startTime = new Date();
//            startTime.setHours(8);
//            Date endTime = new Date();
//            endTime.setHours(23);
//            filialRepository.save(new Filial(1l,"Sergeli","Сергели",startTime,endTime,qattadir));
//            filialRepository.save(new Filial(2l,"Chilonzor","Чилонзар",startTime,endTime,qatdir));

//            categoryRepository.save(new Category(1l,"Telefonlar","Телефоны"));
//            categoryRepository.save(new Category(2l,"Avtomabillar","Машины"));

//            Timestamp start = new Timestamp(2022,8,12,8,0,0,0);
//            Timestamp end = new Timestamp(2022,12,12,22,0,0,0);
//            saleRepository.save(new Sale(1l,"Super aksiya","Супер акция",start,end,100000d,5d));

        }
    }
}
