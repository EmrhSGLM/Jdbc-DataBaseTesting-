Feature: Kullanici DataBase baglanıp bilgileri okuyabilir

  @db
  Scenario: TC01 kullanici database baglanıp istedigi bilgileri okuyabilmeli

    Given kullanici HMC veri tabanina baglanir
    #1.Step => DataBase baglanacagiz
    And kullanici "tHOTELROOM" tablosundaki "Price" verilerini alir
    # 2.Step => Query calistiracagiz select Price from tHOTELROOM
    And kullanici "Price" sutunundaki verileri okur
    # 3.Step => DataBase sorgusu sonunda bize dondurulen bilgiyi nasil kullanabilecegimizi gorecegiz

