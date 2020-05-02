package main.java.main;

import main.java.PlayerInfo.*;

import static main.java.PlayerInfo.PlayerInfoList.getPlayerInfoList;

public class SetInfoList {
    public SetInfoList(){
        PlayerInfoList list=getPlayerInfoList();
        /*/TS第１弾
        list.addInfo(new PitcherInfo(Position.CLOSER,79,74,65,46,67,66,new PitchInfo(Pitches.FASTBALL,30,0,149),new PitchInfo(Pitches.SLIDER,40,20,130),new PitchInfo(Pitches.SLOW_CURVE,30,30,101),new PitchInfo(Pitches.FORK,60,40,134),new PitchInfo(Pitches.TWO_SEAM,20,0,145),new PitchInfo(Pitches.CUT,30,20,140),new PitchInfo(Pitches.CHANGEUP,30,10,128)));//久保
        *list.addInfo(new BatterInfo(81,89,67,Dandou.POWER,45,66,84,58,Position.RIGHT));//バレンティン
        /*list.addInfo(new BatterInfo(84,58,73,Dandou.GROUNDER,63,40,62,82,Position.SECOND));//平野
        //広島
        list.addInfo(new PitcherInfo(Position.SETUPPER,75,58,60,42,50,50,new PitchInfo(Pitches.MOVINGFAST,50,0,144),new PitchInfo(Pitches.SLOW_CURVE,20,30,101),new PitchInfo(Pitches.CHANGEUP,30,20,128)));//アドゥワ
        list.addInfo(new PitcherInfo(Position.STARTER,76,72,77,50,52,68,new PitchInfo(Pitches.FASTBALL,30,0,152),new PitchInfo(Pitches.CUT,50,30,143),new PitchInfo(Pitches.CHANGEUP,40,30,131),new PitchInfo(Pitches.SPIKE_CURVE,30,40,128),new PitchInfo(Pitches.FAST_SCREW,40,20,140)));//ジョンソン
        //ヤクルト
        list.addInfo(new PitcherInfo(Position.STARTER,73,73,78,43,53,67,new PitchInfo(Pitches.FASTBALL,20,0,152),new PitchInfo(Pitches.CUT,40,40,143),new PitchInfo(Pitches.DROPCURVE,20,40,119),new PitchInfo(Pitches.CHANGEUP,60,40,131)));//ブキャナン
        list.addInfo(new PitcherInfo(Position.STARTER,70,74,70,50,58,70,new PitchInfo(Pitches.FASTBALL,30,0,149),new PitchInfo(Pitches.CUT,10,40,140),new PitchInfo(Pitches.CHANGEUP,30,30,128)));//ハフ
        list.addInfo(new PitcherInfo(Position.SETUPPER,75,66,64,51,46,54,new PitchInfo(Pitches.FASTBALL,30,0,149),new PitchInfo(Pitches.SLIDER,40,50,130),new PitchInfo(Pitches.DROPCURVE,30,30,117),new PitchInfo(Pitches.FORK,20,50,134),new PitchInfo(Pitches.CHANGEUP,30,20,137),new PitchInfo(Pitches.CUT,10,20,140)));//近藤
        list.addInfo(new PitcherInfo(Position.CLOSER,81,77,62,41,52,59,new PitchInfo(Pitches.FASTBALL,60,0,152),new PitchInfo(Pitches.DROPCURVE,30,40,119),new PitchInfo(Pitches.VERTICAL_SLIDER,50,50,132),new PitchInfo(Pitches.FORK,30,40,137)));//石山
        list.addInfo(new BatterInfo(54,59,65,Dandou.LOW,63,64,80,71,Position.CATCHER));//中村
        */list.addInfo(new BatterInfo(54,59,1,Dandou.LOW,63,64,99,1,Position.CATCHER));//テスト
        /*list.addInfo(new BatterInfo(80,82,82,Dandou.POWER,60,52,65,79,Position.SECOND));//山田
        list.addInfo(new BatterInfo(64,67,68,Dandou.MEDIUM,57,64,51,72,Position.SHORT));//西浦
        list.addInfo(new BatterInfo(75,69,75,Dandou.LINE_DRIVE,52,40,88,66,Position.RIGHT));//雄平
        list.addInfo(new BatterInfo(59,72,58,Dandou.HIGH,48,51,51,57,Position.THIRD));//村上
        //阪神
        list.addInfo(new PitcherInfo(Position.CLOSER,80,72,60,47,19,75,new PitchInfo(Pitches.FASTBALL,50,0,156),new PitchInfo(Pitches.SLIDER,20,20,136),new PitchInfo(Pitches.SFF,50,60,144),new PitchInfo(Pitches.TWO_SEAM,40,0,152)));//ドリス
        list.addInfo(new PitcherInfo(Position.SETUPPER,78,81,62,46,42,59,new PitchInfo(Pitches.FAST_SLIDER,60,40,139),new PitchInfo(Pitches.MOVINGFAST,50,0,148)));//桑原
        list.addInfo(new PitcherInfo(Position.SETUPPER,76,70,70,56,51,64,new PitchInfo(Pitches.FASTBALL,60,0,150),new PitchInfo(Pitches.SLIDER,40,40,131),new PitchInfo(Pitches.FORK,40,40,135),new PitchInfo(Pitches.TWO_SEAM,20,0,146),new PitchInfo(Pitches.CHANGEUP,30,20,129)));//能見
        list.addInfo(new PitcherInfo(Position.STARTER,70,80,80,71,63,5,new PitchInfo(Pitches.FASTBALL,40,0,148),new PitchInfo(Pitches.CUT,40,20,139),new PitchInfo(Pitches.CURVE,20,30,116),new PitchInfo(Pitches.VERTICAL_SLIDER,30,10,128),new PitchInfo(Pitches.CHANGEUP,50,30,128),new PitchInfo(Pitches.SLOW_CURVE,10,20,101),new PitchInfo(Pitches.FAST_SLIDER,40,40,135),new PitchInfo(Pitches.FAST_HARD_SINKER,20,30,146)));//西
        list.addInfo(new BatterInfo(61,67,69,Dandou.MEDIUM,70,77,80,73,Position.CATCHER));//梅野
        list.addInfo(new BatterInfo(73,61,72,Dandou.LOW,50,58,64,56,Position.SECOND));//糸原
        list.addInfo(new BatterInfo(72,60,69,Dandou.MEDIUM,44,48,66,66,Position.SHORT));//北条
        list.addInfo(new BatterInfo(79,76,79,Dandou.LINE_DRIVE,44,49,87,48,Position.RIGHT));//糸井
        list.addInfo(new BatterInfo(69,74,71,Dandou.HIGH,55,76,80,62,Position.THIRD));//大山

        //巨人*/
        list.addInfo(new PitcherInfo(Position.STARTER,84,85,85,52,50,80,new PitchInfo(Pitches.FASTBALL,60,0,154),new PitchInfo(Pitches.CUT,60,40,145),new PitchInfo(Pitches.SLOW_CURVE,30,40,105),new PitchInfo(Pitches.VERTICAL_SLIDER,50,50,134),new PitchInfo(Pitches.FAST_SINKER,10,10,141),new PitchInfo(Pitches.ONE_SEAM,30,0,150),new PitchInfo(Pitches.SLIDER,70,70,133),new PitchInfo(Pitches.HARD_SINKER,70,70,140),new PitchInfo(Pitches.CHANGEUP,40,40,150),new PitchInfo(Pitches.CURVE,30,30,121),new PitchInfo(Pitches.FORK,40,50,138)));//菅野
        /*list.addInfo(new PitcherInfo(Position.STARTER,79,67,80,45,47,75,new PitchInfo(Pitches.FASTBALL,40,0,153),new PitchInfo(Pitches.SLIDER,50,30,134),new PitchInfo(Pitches.FORK,60,50,137),new PitchInfo(Pitches.HARD_SINKER,20,20,148),new PitchInfo(Pitches.CHANGEUP,30,20,132),new PitchInfo(Pitches.ONE_SEAM,10,0,149),new PitchInfo(Pitches.SLURVE,10,20,134)));//山口
        list.addInfo(new PitcherInfo(Position.SETUPPER,79,74,60,42,70,80,new PitchInfo(Pitches.FASTBALL,50,0,158),new PitchInfo(Pitches.SLIDER,60,50,138),new PitchInfo(Pitches.CURVE,10,10,124),new PitchInfo(Pitches.SFF,60,50,146)));//マシソン
        list.addInfo(new PitcherInfo(Position.SETUPPER,70,64,68,38,42,62,new PitchInfo(Pitches.FASTBALL,30,0,152),new PitchInfo(Pitches.CUT,10,10,143),new PitchInfo(Pitches.POWER_CURVE,40,40,133),new PitchInfo(Pitches.FORK,20,20,137),new PitchInfo(Pitches.CHANGEUP,10,20,131),new PitchInfo(Pitches.DROPCURVE,30,40,119),new PitchInfo(Pitches.TWO_SEAM,10,0,148)));//吉川
        list.addInfo(new BatterInfo(83,76,75, Dandou.HIGH,60,61,80,81, Position.SHORT));//坂本
        list.addInfo(new BatterInfo(68,73,73,Dandou.MEDIUM,55,40,60,70,Position.CENTER));//陽
        list.addInfo(new BatterInfo(60,57,60,Dandou.MEDIUM,40,76,78,68,Position.CATCHER));//炭谷
        list.addInfo(new BatterInfo(64,64,80,Dandou.LOW,62,52,70,78,Position.SECOND));//吉川
        list.addInfo(new BatterInfo(58,78,61,Dandou.POWER,35,57,75,56,Position.THIRD));//ビヤヌエバ

        list.addInfo(new BatterInfo(66,75,79,Dandou.LINE_DRIVE,57,59,56,66,Position.RIGHT));//梶谷
        list.addInfo(new BatterInfo(72,63,84,Dandou.LOW,81,78,70,88,Position.SHORT));//源田
        list.addInfo(new BatterInfo(85,84,80,Dandou.ARTISTE,53,52,86,60,Position.CENTER));//柳田
        list.addInfo(new BatterInfo(71,68,80,Dandou.MEDIUM,67,74,58,70,Position.SHORT));//田中
        list.addInfo(new BatterInfo(80,81,72,Dandou.POWER,49,65,88,64,Position.RIGHT));//鈴木
        list.addInfo(new BatterInfo(63,62,81,Dandou.LOW,69,60,60,74,Position.SHORT));//京田
        list.addInfo(new BatterInfo(80,71,74,Dandou.HIGH,71,67,73,78,Position.RIGHT));//平田*/
    }
}
