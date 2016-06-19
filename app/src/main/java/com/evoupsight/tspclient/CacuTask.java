package com.evoupsight.tspclient;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by evoup on 16-6-19.
 */
public class CacuTask extends AsyncTask<Void, String, Void> {
    private String mChromoStr;

    public CacuTask(String str) {
        this.mChromoStr = str;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Socket so = null;
        try {
            so = new Socket("567393.eicp.net", 15866);
            so.setSoTimeout(1000 * 60 * 5);
            DataOutputStream dos = new DataOutputStream(so.getOutputStream());
            DataInputStream dis = new DataInputStream(so.getInputStream());
            dos.writeBytes(this.mChromoStr + "\n");
            String resStr = dis.readLine();
            if (resStr != null && !resStr.startsWith("ERR")) {
                //Toast.makeText(this,"正在进行云计算排序",Toast.LENGTH_LONG).show();
                publishProgress("正在进行云计算排序");
            } else {
                //Toast.makeText(this,"服务端发生错误", Toast.LENGTH_LONG).show();
                publishProgress("服务端发生错误");
            }
        } catch (IOException e) {
            //Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show();
            publishProgress("网络错误");
        } finally {
            try {
                if (so != null) so.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
