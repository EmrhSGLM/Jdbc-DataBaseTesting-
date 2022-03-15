Feature: US1004 kullanici kayıtlari update eder

  Scenario: TC04 kullanici IDHotel degeri verilen Email'i Update edebilmeli

    Given kullanici DBUtil ile HMC veri tabanina baglanir
    Then tHotel Tablosunda IDHotel degeri 1 olan kaydin Email bilgisini "emrh@gmail.com" yapar