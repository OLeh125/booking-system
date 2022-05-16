package com.learn.booking.bookingsystem.service.mongo;

public interface MongoMigrationFacade {

    void migrateAllUsers();

    void migrateAllEvents();

    void migrateAllAccounts();

    void migrateAllTickets();

    void migrateAllOrders();

    void migrateAllData();
}
