package com.schoolms.model;

import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @chinese xzw
 * @since 2020-06-05
 */
@TableName("t_score")
public class TScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private String number;
    private String chinese;
    private String math;
    private String english;
    public TScore(){
    	
    }
    public String getnumber() {
        return number;
    }

    public void setnumber(String number) {
        this.number = number;
    }

    public String getchinese() {
        return chinese;
    }

    public void setchinese(String chinese) {
        this.chinese = chinese;
    }

   
	public String getmath() {
		return math;
	}

	public void setmath(String math) {
		this.math = math;
	}
	public String getenglish() {
		return english;
	}

	public void setenglish(String english) {
		this.english = english;
	}

	 @Override
	    public String toString() {
	        return "TScore{" +
	        "number=" + number +
	        ",chinese=" + chinese +
	        ",math=" + math +
	        ",english=" + english +
	        "}";
	    }


}
