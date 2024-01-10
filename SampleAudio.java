import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.*;

   
public class SampleAudio extends JFrame {
    
    private final String pathToClip = "audio/gemilang.wav";
    private JButton playBtn;
    private boolean soundLoaded;
    private Clip clip;
   
    public void initialize() {
      setSize(300,200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
        
      // try to load the sound from the file...
      try {
         // Open an audio input stream.
         File file = new File(pathToClip);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
         
         // Get a sound clip resource, open audio clip, and 
         // load samples from the audio input stream.
         clip = AudioSystem.getClip();
         clip.open(audioIn);
         soundLoaded = true;
      } 
      catch (UnsupportedAudioFileException e) {
         soundLoaded = false; 
         e.printStackTrace();
      } 
      catch (IOException e) {
         soundLoaded = false; 
         e.printStackTrace();
      } 
      catch (LineUnavailableException e) {
         soundLoaded = false; 
         e.printStackTrace();
      }

      // make a button to play sound clip...
      playBtn = new JButton(">> Play >>");
      playBtn.setBounds(20, 20,  200,  50);
      
      playBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (soundLoaded) {
                    	if (clip.isRunning())
                            clip.stop();   // Stop the player if it is still running
                        clip.setFramePosition(0); // rewind to the beginning
                        clip.start();     // Start playing   			
                }
            }});
      
      add(playBtn);
      setVisible(true);
   }
    
    public static void main(String[] args) {
		SampleAudio sa = new SampleAudio();
		sa.initialize();
	}
}
