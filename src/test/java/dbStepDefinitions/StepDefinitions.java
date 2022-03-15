package dbStepDefinitions;


import io.cucumber.java.en.Given;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {

    String url="jdbc:sqlserver://184.168.194.58:1433;databaseName=hotelmycamp ; " +
            "user=techproed;password=P2s@rt65"; // authentication lı url
    String username="techproed";
    String password="P2s@rt65";
    // url DataBase i nerede bulacagımı soyler
    // 184.168.194.58 => Bu www.... adresinin asıl degeri IP adresi bu adres e ozel UNIQUE
    // 1433; => Islemcinin bir suru odası var bu odaya git senin sifre burayı acıyor
    // databaseName=hotelmycamp ; "user=techproed;password=P2s@rt65" => Bu bir authentecitaion
    //     Bu  authentecitaion Servis yoneticisinden alırız
    // databaseName=hotelmycamp => Kullanacagımın database in ismi
    // user=techproed;password=P2s@rt65"  => DataBase ulasmak icin kullanıcı adı ve sifre

    Connection connection;
    Statement statement;
    ResultSet resultSet;



    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        // DataBase'e baglantı kuruyoruz
        connection = DriverManager.getConnection(url, username, password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        // Query calistiracagiz
        // Query miz boyle olabilir  => SELECT Price FROM tHOTELROOM
        String readQuery = "SELECT "+ field +" FROM " + table ;
        resultSet = statement.executeQuery(readQuery);
        // sorgu objemiz statement ile calistirabiliriz
        // statement.execute(readQuery); bu kod bana resultSet dondurur
    }
    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {
        // resultSet bizim su ana kadar kullandıgımız set yapisinda degildir
        // resultSet iterator ile calisir

        // resultSet de ki bilgileri okumak istiyorsanız
        // once iterator'u istedigimiz yere gondermelisiniz

        resultSet.first(); // Bu komut iterator'u ilk elemente goturur; gitti ise true, gidemedi ise false doner

        // iterator istenen konuma gidince artık elementi yazdirabiliriz
        System.out.println("1. Eleman = " + resultSet.getString(field));

        // ikinci oda fiyatini gormek istersek
        // iterator'u yollamamiz lazim

        resultSet.next();

        System.out.println("2. Eleman = " + resultSet.getString(field));

        System.out.println(resultSet.next()); //true

        System.out.println(resultSet.getString(field));
        // resultSet.next() nerede olursa olsun iterator'u bir sonrakine gecirir

        // tum Price sutununu yazdirmak istesem
        System.out.println("====================================");
        // resultSet ile calisirken iterator komutnu kontrol etmek zorundayiz
        // eger 1. elemana donmeden listeyi yazdirmaya devam edersem
        // kaldigi yerden deavam edip 4. element ve sonrasını yazdirir

        resultSet.absolute(0);
        // resultSet.absolute(0); => belirteci istedigimiz index'e getirebiliriz bu method ile
        int i=1;
        while(resultSet.next()){
            System.out.println(i+". Eleman = " + resultSet.getString(field));
            i++;
        }

        // Price sutununda kac data oldugunu bulalım
        // while loop ile resultSet.next() false donunceye kadar gittik
        // dolayisiyla artik iterator sonda

        // resultSet.getRow(); => Hamgi row dasın
        // resultSet.first(); => Ilk row'a git
        // resultSet.last();  => Son row'a git
        // resultSet.absolute(5); => 5. row'a git
        // resultSet.getObject("BookName"); => BookName row daki objeyi ver
        // resultSet.getString("BookName"); => BookName row daki degeri ver

        resultSet.last(); // Son elemana göndeririz ve son elemantin satır sayısını yazdiririz
        System.out.println("Toplam Eleman Sayisi : " + resultSet.getRow());

        // su anda tum Price bligileri resultSet uzerinde
        // eger bu bilgilerle birden fazla test yapacaksak
        // bu bilgileri java ortamına almakta fayda var
        // Ex: bir List<Double> olusturup tum Price verilerini bu listeye ekleyebiliriz
        // boylece bir java objesi olan List sayesinde
        // Price degerleri uzerinde istedigimiz testleri yapabiliriz

        resultSet.absolute(0);
        List<Double> priceList = new ArrayList<>();

        while(resultSet.next()){
            priceList.add(resultSet.getDouble(field));
        }
        System.out.println("priceList : " + priceList);
        // DataBase de ki tum bilgileri priceList ime aldım
        // Artık bu liste uzerinden tum islemlerimi yapabilirim

    }
}
