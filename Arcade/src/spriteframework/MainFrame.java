package spriteframework;

import javax.swing.JFrame;

public abstract class MainFrame extends JFrame  {

    // hotspot
    protected abstract AbstractBoard createBoard();
	protected abstract int getBoardWidth();
	protected abstract int getBoardHeight();
    
    public MainFrame(String t) {

        add(createBoard());
		setTitle(t);
		setSize(getBoardWidth(), getBoardHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
    }


//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(() -> {
//
//            MainFrameExtended ex = new MainFrameExtended();
//        });
//    }
    
}
