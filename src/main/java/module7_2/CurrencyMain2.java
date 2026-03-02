package module7_2;

import module7_2.View.CurrencyConverterView2;
import module7_2.Datasource.MariaDbConnection;
import module7_2.Dao.CurrencyDao;

public class CurrencyMain2 {
    public static void main(String[] args) {
        MariaDbConnection.getConnection();
        CurrencyDao currencyDao = new CurrencyDao();
        double rate = currencyDao.getExchangeRate("USD");
        System.out.println("USD rate: " + rate);
        CurrencyConverterView2.launch(CurrencyConverterView2.class);
    }
}