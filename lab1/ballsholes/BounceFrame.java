package ballsholes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {

    private ObjectCanvas canvas;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private static int hitScore = 0;
    private static JLabel hitScoreLabel;
    private static BallThreadJoin prevThread = null;
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");

        this.canvas = new ObjectCanvas();

        canvas.add(new Hole(10, 10)); 
        canvas.add(new Hole(10, (HEIGHT-130)));
        canvas.add(new Hole((WIDTH-50), 10));
        canvas.add(new Hole((WIDTH-50), (HEIGHT-130)));

        JPanel hitScorePanel =  new JPanel();
        hitScorePanel.setBackground(Color.lightGray.brighter());
        JLabel hitScoreLabel = new JLabel(String.valueOf(BounceFrame.hitScore));
        BounceFrame.hitScoreLabel = hitScoreLabel;
        hitScorePanel.add(hitScoreLabel);

        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(hitScorePanel, BorderLayout.NORTH);
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray.brighter());

        JButton buttonAddGray = new JButton("Add Gray");
        //JButton buttonAddRed = new JButton("Add Red");
        //JButton buttonAddBlue = new JButton("Add Blue");
        JButton buttonTest = new JButton("Run Run Run!");
        JButton buttonJoinGray = new JButton("Join Gray");
        JButton buttonJoinRed = new JButton("Join Red");
        JButton buttonJoinBlue = new JButton("Join Blue");

        buttonAddGray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBall(0);
            }
        });

        /*buttonAddRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBall(1);
            }
        });

        buttonAddBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 100; i++) {
                    createBall(-1);
                }
            }
        });*/

        buttonTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 500; i++) {
                    createBall(-1);
                }
                createBall(1);
            }
        });

        buttonJoinGray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 1; i++) {
                    createBallJoin(0);
                }
            }
        });
        buttonJoinRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 1; i++) {
                    createBallJoin(1);
                }
            }
        });
        buttonJoinBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 1; i++) {
                    createBallJoin(-1);
                }
            }
        });

        buttonPanel.add(buttonAddGray);
        //buttonPanel.add(buttonAddRed);
        //buttonPanel.add(buttonAddBlue);
        buttonPanel.add(buttonTest);
        buttonPanel.add(buttonJoinGray);
        buttonPanel.add(buttonJoinRed);
        buttonPanel.add(buttonJoinBlue);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
    public static synchronized void hitScoreInc() {
        hitScore++;
        hitScoreLabel.setText(String.valueOf(hitScore));
    }

    public void createBall(int priority){
        Ball b = new Ball(canvas, priority);
        canvas.add(b);

        BallThread thread = new BallThread(b);
        thread.start();
        System.out.println("Created Ball = " + thread.getName());
    }

    public void createBallJoin(int priority){
        Ball b = new Ball(canvas, priority);
        canvas.add(b);

        BallThreadJoin thread;
        if(prevThread == null){
            thread = new BallThreadJoin(b, null);
        } else{
            thread = new BallThreadJoin(b, prevThread);
        }
        thread.start();
        prevThread = thread;
        System.out.println("Created Ball = " + thread.getName());
    }
}
