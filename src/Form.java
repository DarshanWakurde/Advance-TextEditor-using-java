import java.awt.*;

public class Form {

    Form(){
        Font f1=new Font("Bold",20,20);
        Frame f=new Frame();
        Label l=new Label("Personal Details");
        l.setBounds(50,50,200,30);
        l.setFont(f1);



        Label l2=new Label("Name");
        l2.setBounds(50,100,50,30);

        TextField t1=new TextField();
        t1.setBounds(110,100,50,30);

        CheckboxGroup cgr=new CheckboxGroup();
        Checkbox c=new Checkbox("Male",cgr,true);
        c.setBounds(50,140,50,50);
        Checkbox c2=new Checkbox("Female",cgr,false);
        c2.setBounds(110,140,50,50);

        Checkbox com=new Checkbox("Computer");
        com.setBounds(50,190,50,50);
        Checkbox spt=new Checkbox("Sports");
        spt.setBounds(110,190,50,50);
        Button b=new Button("Submit");
        b.setBounds(110,245,70,20);

        f.add(l);f.add(l2);f.add(t1);f.add(c);f.add(c2);f.add(com);f.add(spt);f.add(b);



        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String [] args){

        new Form();

    }
}
