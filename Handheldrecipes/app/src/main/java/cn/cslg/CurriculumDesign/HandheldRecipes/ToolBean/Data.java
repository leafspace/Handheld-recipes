package cn.cslg.CurriculumDesign.HandheldRecipes.ToolBean;

import android.app.Application;

import java.util.Random;

/**
 * Created by Administrator on 2017/6/4.
 * LastEdit: 2017-6-4
 * Contact me:
 *     Phone: 18852923073
 *     E-mail: 18852923073@163.com
 */
public class Data extends Application {
    private boolean[] isGood;
    private boolean[] isAdd;
    private int[] choice;
    private int isExist;

    private int[] karulyList;
    private int[] healtyList;
    private int[] satietyList;

    @Override
    public void onCreate(){
        super.onCreate();

        this.isGood = new boolean[10];
        this.isAdd = new boolean[10];
        this.choice = new int[10];
        this.karulyList = new int[10];
        this.healtyList = new int[10];
        this.satietyList = new int[10];
        for(int i = 0; i < 10; ++i) {
            this.isGood[i] = false;
            this.isAdd[i] = false;
        }
        this.isExist = 0;
        for(int i = 0; i < 10; ++i) {
            this.karulyList[i] = new Random().nextInt(10);
            this.healtyList[i] = new Random().nextInt(10);
            this.satietyList[i] = new Random().nextInt(10);
        }
    }

    public void setIsGood(int index) {
        if(index < 10) {
            this.isGood[index] = true;
        }
    }

    public void setIsAdd(int index) {
        if(index < 10) {
            this.isAdd[index] = true;
        }
    }

    public void removeIsAdd(int index) {
        if(index < 10) {
            this.isAdd[index] = false;
        }
    }

    public boolean getIsGood(int index) {
        if(index < 10) {
            return this.isGood[index];
        }
        return false;
    }

    public boolean getIsAdd(int index) {
        if(index < 10) {
            return this.isAdd[index];
        }
        return false;
    }

    public boolean addFood(int index) {
        if(this.isExist == 10) {
            return false;
        }
        this.choice[this.isExist++] = index;
        return true;
    }

    public boolean removeFood(int index) {
        int i;
        for(i = 0; i < this.isExist; ++i) {
            if(this.choice[i] == index) {
                break;
            }
        }

        if(i == this.isExist) {
            return false;
        }
        for(i = 0; i < this.isExist; ++i) {
            if(i == 9) {
                this.choice[i] = 0;
                break;
            }
            this.choice[i] = this.choice[i + 1];
        }
        return true;
    }

    public int[] getChoice() {
        return this.choice;
    }

    public int getIsExist() {
        return this.isExist;
    }

    public int[] getKarulyList() {
        return this.karulyList;
    }

    public int[] getHealtyList() {
        return this.healtyList;
    }

    public int[] getSatietyList() {
        return this.satietyList;
    }
}