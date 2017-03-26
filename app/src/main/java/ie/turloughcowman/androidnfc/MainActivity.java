package ie.turloughcowman.androidnfc;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.math.BigInteger;

/**
 * Created by Turlough
 */
public class MainActivity extends AppCompatActivity {

    private TextView result;
    private NfcAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.textView_explanation);
        adapter = NfcAdapter.getDefaultAdapter(this);

        if (adapter == null)
            result.setText("NFC is not supported on this device");
        else if (!adapter.isEnabled())
            result.setText("NFC is disabled.");
        else
            prepareNfc();

    }


    private void prepareNfc() {

        result.setText("Prepare to Scan...");

        adapter.enableReaderMode(
                this,
                x -> {
                    byte[] id = x.getId();
                    String numberFormat = "%0" + (id.length * 2) + "X";
                    String text = String.format(numberFormat, new BigInteger(1, id));
                    result.post(() -> result.setText(text));
                },
                NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, new Bundle()
        );

    }



}
