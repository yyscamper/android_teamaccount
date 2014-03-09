package com.yyscamper.teamaccount;

import java.util.List;

import com.baidu.frontia.*;
import com.baidu.frontia.api.*;
import com.baidu.frontia.api.FrontiaStorageListener.*;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SyncActivity extends Activity {
	private FrontiaStorage _cloudStorage;
	private String _lastModifyDate;
	private String _lockStatus;
	private TextView _statusView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupViews();

		_cloudStorage = Frontia.getStorage();
	}
	
	private void setupViews() {
		setContentView(R.layout.activity_sync);

		_statusView = (TextView) findViewById(R.id.textViewSyncStatus);
	}
		

	protected void saveData() {
		final FrontiaData[] datas = new FrontiaData[3];

        datas[0] = new FrontiaData();
        datas[0].put("lastModifyDate", _lastModifyDate);
        datas[0].put("lockStatus", _lockStatus);

        datas[1] = new FrontiaData();
        datas[1].put("money", 100);
        datas[1].put("payer", "Felix");

        datas[2] = new FrontiaData();
        datas[2].put("money", 120);
        datas[2].put("payer", "Andy");

        for(int i=0;i<datas.length;i++){
            _cloudStorage.insertData(datas[i],
                    new FrontiaStorageListener.DataInsertListener() {

                        @Override
                        public void onSuccess() {
                        	_statusView.setText("save data success\n");
                        }

                        @Override
                        public void onFailure(int errCode, String errMsg) {
                        	_statusView.setText("errCode:" + errCode
                                    + ", errMsg:" + errMsg);
                        }

                    });
        }

	}

	protected void deleteData() {
		//FrontiaQuery中有很多查询条件，你可以尝试多种查询条件，相当于sql语句中的where
		FrontiaQuery q1 = new FrontiaQuery();
        q1.equals("animal", "Panda");
        FrontiaQuery q2 = new FrontiaQuery();
        q2.equals("animal", "Dragon");
        FrontiaQuery q3 = new FrontiaQuery();
        q3.equals("animal", "Cat");
        FrontiaQuery q4 = new FrontiaQuery();
        q4.equals("animal", "Dog");
        FrontiaQuery query = q1.or(q2).or(q3).or(q4);

        _cloudStorage.deleteData(
						query,
						new DataOperationListener() {

							@Override
							public void onSuccess(long count) {
								if (null != _statusView) {
									_statusView.setText("delete " + count
											+ " data.");
								}
							}

							@Override
							public void onFailure(int errCode, String errMsg) {
								if (null != _statusView) {
									_statusView.setText("errCode:"
											+ errCode + ", errMsg:" + errMsg);
								}
							}
						});
	}

	protected void updateData() {
        FrontiaQuery query = new FrontiaQuery();
        query.lessThan("number", 20);

        FrontiaData newData = new FrontiaData();
        newData.put("number",20);
        newData.put("animal", "Dog");

        _cloudStorage.updateData(
						query,
						newData,
						new DataOperationListener() {

							@Override
							public void onSuccess(long count) {
								if (null != _statusView) {
									_statusView.setText("update " + count
											+ " data.");
								}
							}

							@Override
							public void onFailure(int errCode, String errMsg) {
								if (null != _statusView) {
									_statusView.setText("errCode:"
											+ errCode + ", errMsg:" + errMsg);
								}
							}
						});
	}

	protected void findData() {
		//空的FrontiaQuery表示query所有的数据(具有可读权限数据才能被查到)
		FrontiaQuery query = new FrontiaQuery();

		_cloudStorage.findData(query,
				new DataInfoListener() {

					@Override
					public void onSuccess(List<FrontiaData> dataList) {
						if (null != _statusView) {
                            StringBuilder sb = new StringBuilder();
                            int i = 0;
                            for(FrontiaData d : dataList){
                                sb.append(i).append(":").append(d.toJSON().toString()).append("\n");
                                i++;
                            }
                            _statusView.setText("find data\n"
									+ sb.toString());
						}
					}

					@Override
					public void onFailure(int errCode, String errMsg) {
						if (null != _statusView) {
							_statusView.setText("errCode:" + errCode
									+ ", errMsg:" + errMsg);
						}
					}
				});

	}

}
