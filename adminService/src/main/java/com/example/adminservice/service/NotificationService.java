package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.NotificationDto;
import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Notification;
import com.example.adminservice.entity.User;
import com.example.adminservice.repository.AttachmentRepository;
import com.example.adminservice.repository.NotificationRepository;
import com.example.adminservice.repository.UserRepository;
import com.example.adminservice.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
        return ApiResponse.builder().success(true).message("Saved!").data(save).build();
    }

    public ApiResponse getAll() {
        List<Notification> all = notificationRepository.findAll();
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(all).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Notification yo`q \uD83D\uDE1C").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDC4C").data(byId.get()).build();
    }

    public ApiResponse delete(Long id){
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Noto`g`ri Id kiritildi \uD83D\uDE14").build();
        }
        notificationRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
    }
}
