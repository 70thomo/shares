package net.dixq.irairabar;


public class Vec {
	
	public float _x, _y;
	
	Vec(){
		_x = _y = 0;
	}
	
	Vec( float x, float y ){
		_x = x;
		_y = y;
	}
	
	//Šp“x‚ðŽæ“¾‚·‚é
	float getAngle(){
		return (float)Math.atan2(_y, _x);
	}

	//‘å‚«‚³‚ðŽæ“¾‚·‚é
	float getLength(){
		return (float)Math.sqrt( _x*_x + _y*_y );
	}
	
	//ˆø”‚Ì’l‚æ‚è‘å‚«‚³‚ª‘å‚«‚¯‚ê‚Îˆø”‚Ì’l‚É‚·‚é
	void setLengthCap( float maxLength ){
		float len = getLength();
		if( maxLength == 0 ){
			return;	//0Š„–hŽ~
		}
		if( len > maxLength ){//maxLength‚æ‚è‘å‚«‚¯‚ê‚Î‘å‚«‚³‚ðmaxLength‚É‚·‚é
			float rate =len/maxLength;
			_x /= rate;
			_y /= rate;
		}
	}
	
	// vec•ûŒü‚ÌŒü‚«‚Örate—¦‚Ù‚ÇƒuƒŒƒ“ƒh‚·‚é
	void blend( Vec vec, float rate ){
		float w = vec._x - _x;
		float h = vec._y - _y;
		_x += w*rate;
		_y += h*rate;
	}
	
}
