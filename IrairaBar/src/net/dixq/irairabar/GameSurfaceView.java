package net.dixq.irairabar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
	private GameMgr _gameMgr = new GameMgr();
	private Thread _thread;
	
	private SurfaceHolder ho;
	
	Resources res = this.getContext().getResources();
	Bitmap rob = BitmapFactory.decodeResource(res,R.drawable.robo);

	public GameSurfaceView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		//�𑜓x���ύX�ʒm 
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		_thread = new Thread(this);		//�ʃX���b�h�Ń��C�����[�v�����
		_thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		_thread = null;
	}

	@Override
	public void run() {
		while (_thread!=null) {	//���C�����[�v
			_gameMgr.onUpdate();
			onDraw(getHolder());
		}
	}

	private void onDraw(SurfaceHolder holder) {
		

		
		Canvas c = holder.lockCanvas();
		_gameMgr.onDraw(c);
		holder.unlockCanvasAndPost(c);
	}
}

