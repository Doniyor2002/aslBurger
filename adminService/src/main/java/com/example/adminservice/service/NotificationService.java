package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.NotificationDto;
import com.example.adminservice.dto.ResDiscountDto;
import com.example.adminservice.dto.ResNotification;
import com.example.adminservice.entity.*;
import com.example.adminservice.repository.AttachmentRepository;
import com.example.adminservice.repository.NotificationRepository;
import com.example.adminservice.repository.UserRepository;
import com.example.adminservice.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;
    private final DateFormatUtil dateFormatUtil;

    public ApiResponse save(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setNameUz(notificationDto.getNameUz());
        notification.setNameRu(notificationDto.getNameRu());
        notification.setTitle(notificationDto.getTitle());
        notification.setBody(notificationDto.getBody());
        notification.setHasBot(notificationDto.isHasBot());

        //NotificationDto da sendTime (yyyy-MM-dd HH:mm:ss) ko`rinishda yoziladi
        Timestamp timestamp = dateFormatUtil.stringTimeConvertorToTimestamp(notificationDto.getSendTime());
        notification.setSendTime(timestamp);
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(notificationDto.getAttachmentId());
        if (!attachmentOptional.isEmpty()) {
            notification.setAttachment(attachmentOptional.get());
        }


        //id si berilgan User bor bo`lsa notificationga qo`shiladi
        Optional<User> userOptional = userRepository.findById(notificationDto.getUserId());
        try {
            User user = userOptional.get();
            notification.setUser(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        Notification save = notificationRepository.save(notification);
        if (save!=null){
            return ApiResponse.builder().success(true).message("Saved!").data(toDto(save)).build();
        }
        return ApiResponse.builder().success(false).message("Saqlashda xatolik yuz berdi!").build();
    }

    public ApiResponse getAll(int page,int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Notification> all = notificationRepository.findAll(request);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(toDtoPage(all)).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Notification yo`q \uD83D\uDE1C").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDC4C").data(toDto(byId.get())).build();
    }

    public ApiResponse delete(Long id){
        boolean exists = notificationRepository.existsById(id);
        if (exists) {
            notificationRepository.deleteById(id);
            return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
        }
        return ApiResponse.builder().success(false).message("Noto`g`ri Id kiritildi \uD83D\uDE14").build();
    }
    public ResNotification toDto(Notification notification){
        return ResNotification.builder()
                .nameRu(notification.getNameUz())
                .nameUz(notification.getNameUz())
                .body(notification.getBody())
                .attachmentId(notification.getAttachment().getId())
                .hasBot(notification.isHasBot())
                .sendTime(String.valueOf(notification.getSendTime()))
                .title(notification.getTitle())
                .userName(notification.getUser().getFullName())
                .build();
    }

    public Page<ResNotification> toDtoPage(Page<Notification> notificationPage){
        List<ResNotification> collect = notificationPage.stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}
