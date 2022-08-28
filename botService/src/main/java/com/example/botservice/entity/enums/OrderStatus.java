package com.example.botservice.entity.enums;

public enum OrderStatus {
    NEW,// yangi birinchi kelgan ordeer
    PENDING,//
    IN_PROGRESS,//jarayonda-- oshxonada tayyorlanayapti
    READY,//-- oshxonadan tayyor buldi
    APPROVED,//tasdiqladi-- operator orderni tasdiqlab quyadi
    DELIVERED,//yetkazildi -- kureir belgilaydi
    CANCELED, //mijoz tomonidan
    REJECTED //tizim tomonidan
}
// oshxona belgilaydi   -- ready, in_progress,rejected,