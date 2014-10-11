package fei.socketclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button mReceiveBtn;
	TextView mReceiveTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mReceiveBtn = (Button) findViewById(R.id.button_receive);
		mReceiveTv = (TextView) findViewById(R.id.textView_receive);

		mReceiveBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new SocketWorker()).start();
			}
		});
	}

	class SocketWorker implements Runnable {

		@Override
		public void run() {
			try {
				Log.i("lsx", "prepared to start socket linking...");
				Socket socket = new Socket("192.168.191.6", 8889);
				Log.i("lsx", " socket link done..." + socket.getRemoteSocketAddress().toString());
				InputStream in = socket.getInputStream();
				Log.i("lsx", String.valueOf(in.available()) + "¸ö×Ö½Ú");
				byte[] buffer = new byte[in.available()];
				in.read(buffer);
				String msg = new String(buffer);
				
				Log.i("lsx", msg);
				//mReceiveTv.setText(msg);
			} catch (UnknownHostException e) {
				Log.i("lsx", "unknown host...........");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
