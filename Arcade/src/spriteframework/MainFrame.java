package spriteframework;

import javax.swing.JFrame;

public abstract class MainFrame extends JFrame  {

    // hotspot
    protected abstract AbstractBoard createBoard();
	protected abstract GameConfig getGameConfig();
    
    public MainFrame(String t) {

        add(createBoard());
		setTitle(t);
		setSize(getGameConfig().getBORDER_WIDTH(), getGameConfig().getBORDER_HEIGHT());
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
