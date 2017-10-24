package sample;

import javafx.scene.control.TextArea;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cellbody {
    public Map<Integer,Double> initW(){
        Map<Integer,Double> w = new HashMap<>();
        double w1 = (Math.random()*(2-0) - 1);
        double w2 = (Math.random()*(2-0) - 1);
        w.put(0,w1);
        w.put(1,w2);
        return w;
    }
    public List<List<Double>> initX(){
        List<List<Double>> x = new ArrayList<>();
        List<Double> x1 = new ArrayList();
        x1.add(0.5);
        x1.add(-1.0);

        List<Double> x2 = new ArrayList();
        x2.add(-1.0);
        x2.add(-1.0);

        List<Double> x3 = new ArrayList();
        x3.add(2.0);
        x3.add(-1.0);

        List<Double> x4 = new ArrayList();
        x4.add(-2.0);
        x4.add(-1.0);

        x.add(x1);
        x.add(x2);
        x.add(x3);
        x.add(x4);

        return x;
    }

    public Map<List<Double>,Boolean> initXOut(List<List<Double>> x){
        Map<List<Double>,Boolean> xOut = new HashMap<>();
        xOut.put(x.get(0),true);
        xOut.put(x.get(1),false);
        xOut.put(x.get(2),true);
        xOut.put(x.get(3),false);
        return xOut;
    }

    public  Map<Integer,Double> train(Map<Integer, Double> w, List<List<Double>> x,Map<List<Double>,Boolean> xOut, double η ,TextArea textOutput){
        int  stop = 0;
        int  runtimes = 0;
        do{
            runtimes++;
            textOutput.appendText("---第"+runtimes+"次學習\n---");
            stop = 0;
            int xprint = 1;
            for(List<Double> xi:x){
                int xiSize = xi.size();
                double wtx = 0;
                int sgn = 0;

                for(int i=0;i<xiSize;i++){
                    wtx += xi.get(i)*w.get(i);
                }
                if(xOut.get(xi) && wtx<0){
                    sgn = 1;
                    for(int i=0;i<xiSize;i++){
                        double newW = w.get(i)+2*η*sgn*xi.get(i);
                        w.put(i,newW);
                    }
                    stop++;
                }else if(!xOut.get(xi) && wtx>0){
                    sgn = -1;
                    for(int i=0;i<xiSize;i++){
                        double newW = w.get(i)+2*η*sgn*xi.get(i);
                        w.put(i,newW);
                    }
                    stop++;
                }
                textOutput.appendText("w : ");
                for(int key:w.keySet()){
                    DecimalFormat df=new DecimalFormat("#.##");
                    String Strw=df.format(w.get(key));
                    textOutput.appendText(Strw+",");
                }
                textOutput.appendText("  x"+xprint+" : "+sgn+"\n");
                xprint++;
            }
        }while (stop!=0);
        return w;
    }
}
