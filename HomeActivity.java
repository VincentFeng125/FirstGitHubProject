package project.fengzhe;

import project.fengzhe.activity.InitActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class HomeActivity extends Activity {

	private Intent intent = null;

	private GridView mGridView;

	private Map<String , Object> res;

	private List<Map<String , Object>> datas;

	private int[] imagesId = new int[] { R.drawable.ico_input, R.drawable.ico_status, R.drawable.ico_position, R.drawable.ico_search, R.drawable.ico_about };

	private String[] menusContent = new String[] { "车辆信息录入", "车辆状态查询", "车辆位置查询", "车辆信息检索", "关于" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);

		mGridView = (GridView) findViewById(R.id.gridview_menu);
		mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		datas = new ArrayList<Map<String , Object>>();

		for (int i = 0; i < imagesId.length; i++) {
			res = new HashMap<String , Object>();
			res.put("title", menusContent[i]);
			res.put("img", imagesId[i]);
			datas.add(res);
		}

		mGridView.setAdapter(new SimpleAdapter(this, datas, R.layout.grid_item, new String[] { "title", "img" }, new int[] { R.id.grid_tv, R.id.grid_image }));
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {

				switch (position) {
				case 0:
					intent = new Intent(HomeActivity.this, InitActivity.class);
					break;
				case 1:
					intent = new Intent(HomeActivity.this, CarStatusActivity.class);
					break;
				case 2:
					intent = new Intent(HomeActivity.this, PositionActivity.class);
					break;
				case 3:
					intent = new Intent(HomeActivity.this, SearchActivity.class);
					break;
				case 4:
					break;
				}
				if (intent != null) {
					startActivity(intent);
				}

			}
		});
	}
}
