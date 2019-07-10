package com.rocky.newringtones.util;

import android.util.Log;

import java.util.Random;

/**
 * create by 2019/7/9
 * <p>
 * author: wgl
 * <p>
 * Believe in yourself, you can do it.
 */
public class Probability {
    public static boolean probabilityCalculation(ProbabilityEnum probability){
        Random random=new Random();
        int rm ;
        boolean result=false;
        switch(probability){
            case LOWPROBABILITY:
                rm=random.nextInt(5);
                Log.e("Probability", "LOWPROBABILITY\t"+rm );
                if(rm==1){
                    result=true;
                }else{
                    result=false;
                }
                break;
            case SAMEPROBABILITY:
                rm=random.nextInt(2);
                if(rm==1){
                    result=true;
                }else{
                    result=false;
                }
                break;
            case HIGHPROBABILITY:
                rm=random.nextInt(4);
                rm+=1;
                Log.e("Probability", "HIGHPROBABILITY\t"+rm );
                if(rm==1||rm==2||rm==3){
                    result=true;
                }else{
                    result=false;
                }
                break;

        }
        return result;
    }

}
