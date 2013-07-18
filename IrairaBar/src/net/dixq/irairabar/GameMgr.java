package net.dixq.irairabar;

import java.util.ArrayList;
import java.util.LinkedList;
import net.dixq.irairabar.Barricade.eType;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameMgr {
	
	private enum eStatus
	{
		NORMAL,
		GAMEOVER,
		GAMECLEAR
	};

	private static final float PI = (float) Math.PI;
	private ArrayList<Barricade> _barrList = new ArrayList<Barricade>();//障害物リスト
	private LinkedList<Task> _taskList = new LinkedList<Task>();// タスクリスト
	private eStatus _status = eStatus.NORMAL;
	private Player _player;

	GameMgr() {
		_barrList.add(new BarricadeSquare(  0,  0,480, 20, null));// 画面4隅に四角形を配置
		_barrList.add(new BarricadeSquare(  0,  0, 20,800, null));
		_barrList.add(new BarricadeSquare(460,  0, 20,800, null));
		_barrList.add(new BarricadeSquare(  0,780,480, 20, null));
		
		_barrList.add(new BarricadeSquare(  0,220,150, 20, null));

		//_barrList.add(new BarricadeTriangle(  0, 0, 200, new BConf(+PI / 150)));// 左上回転する三角形
		//_barrList.add(new BarricadeTriangle(480, 0, 180, new BConf(+PI / 150)));// 右上回転する三角形

		//_barrList.add(new BarricadeStar(240, 240, 50, 200, new BConf(-PI / 360)));// 中央に回転する星
		//_barrList.add(new BarricadeStar(240, 240, 20,  80, new BConf(+PI / 360)));// 中央に回転する星
		
		_barrList.add(new BarricadeSquare(300, 440, 200, 20, null));//右下の固定通路
		_barrList.add(new BarricadeSquare(250, 520, 130, 20, null));//
		_barrList.add(new BarricadeSquare(330, 620, 130, 20, null));//
		_barrList.add(new BarricadeSquare(300, 80, 200, 20, null));

		_barrList.add(new BarricadeSquare(200, 300, 130, 20, null));//
		_barrList.add(new BarricadeSquare(80, 140, 180, 20, new BConf(-PI / 150)));
		_barrList.add(new BarricadeSquare(90, 380, 200, 20, new BConf(+PI / 150)));
		_barrList.add(new BarricadeSquare(80, 620, 200, 20, new BConf(-PI / 150)));
		
		
		
		//_barrList.add(new BarricadeSquare(230, 390, 20, 350, null));//中央区切り線

		//_barrList.add(new BarricadeSquare(0, 480, 240, 20, new BConf(+PI / 360)));// 左下回転するバー

		//_barrList.add(new BarricadeSquare( 20, 600, 110, 20, new BConf(+PI / 360)));// 左下回転するバー
		//_barrList.add(new BarricadeSquare(130, 600, 110, 20, new BConf(+PI / 360)));// 左下回転するバー
		//_barrList.add(new BarricadeSquare(185, 600,  55, 20, new BConf(+PI / 360)));// 左下回転するバー

		//_barrList.add(new BarricadeSquare(20, 680,  80, 20, null));// ゴールに接触したバー

		_barrList.add(new BarricadeSquare(20, 20,  440, 40, new BConf(eType.GOAL)));// ゴール
		
		for (Barricade bar : _barrList) {
			_taskList.add(bar);	//タスクリストに障害物を追加
		}

		_player = new Player();
		_taskList.add(_player);
		_taskList.add(new FpsController());
	}

	@SuppressWarnings("incomplete-switch")
	private boolean Collision(){
		Vec vec = new Vec();
		final Circle cir = _player.getPt();
		for(Barricade barr : _barrList){
			Def.eHitCode code = barr.isHit(cir, vec);
			switch(code){
			case OUT:
				_status = eStatus.GAMEOVER;
				return true;
			case GOAL:
				_status = eStatus.GAMECLEAR;
				return true;
			}
		}
		return false;
	}
	

	
	public boolean onUpdate() {
		if( _status != eStatus.NORMAL )
		{
			return true;
		}
		//追加
		if(IrairaBarActivity.st==0)
		{
			return true;
			
		}
		if( Collision() ){
			return true;
		}
		for (int i = 0; i < _taskList.size(); i++) {
			if (_taskList.get(i).onUpdate() == false) { // 更新失敗なら
				_taskList.remove(i); // そのタスクを消す
				i--;
			}
		}
		return true;
	}


	private void drawStatus(Canvas c){
		if(IrairaBarActivity.st==0)
		{
			Paint paint = new Paint();
			paint.setTextSize(80);
			paint.setColor(Color.RED);
			c.drawText("GameOver",40,100,paint);
		}
		else
			{
			switch( _status ){
		case GAMEOVER:
			{
				Paint paint = new Paint();
				paint.setTextSize(80);
				paint.setColor(Color.BLACK);
				c.drawText("GameOver", 40, 100, paint);
			}
			break;



		case GAMECLEAR:
			{
				Paint paint = new Paint();
				paint.setTextSize(80);
				paint.setColor(Color.BLACK);
				c.drawText("GameClear", 40, 100, paint);
			}
			break;
		}
	}
 }
	
	public void onDraw(Canvas c) {
		c.drawColor(Color.WHITE); // 白で塗りつぶす
		for (Task task : _taskList) {
			task.onDraw(c);// 描画
		}
		drawStatus(c);
	}

}
