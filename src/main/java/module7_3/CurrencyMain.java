package module7_3;

import module7_3.Dao.CurrencyDao;
import module7_3.Datasource.MariaDbJpaConnection;
import module7_3.View.CurrencyConverterView;

public class CurrencyMain {
    public static void main(String[] args) {
        CurrencyConverterView.launch(CurrencyConverterView.class);
    }
}