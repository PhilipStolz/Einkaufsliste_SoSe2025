package system.outsideinteraction;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

abstract public class GUInterface extends JDialog implements ActionListener {
	
	private class UserInputDlg extends JDialog implements ActionListener {
		private static final long serialVersionUID = 1L;
		private static final int BORDER_WIDTH = 10;
		private static final int VERTICAL_GAP = 20;
		private static final int HORIZONTAL_GAP = 10;
		private static final int TEXT_FIELD_COLUMNS = 50;
		
		UserInputDlg(String title) {
		    this.setTitle(title);
			this.setModal(true);
			this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		    Container contentPane = this.getContentPane();
		    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		    contentPane.add(Box.createHorizontalStrut(BORDER_WIDTH));
		    JPanel mainPanel = new JPanel();
		    contentPane.add(mainPanel);		    
		    contentPane.add(Box.createHorizontalStrut(BORDER_WIDTH));

		    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		    mainPanel.add(Box.createVerticalStrut(BORDER_WIDTH));
		    JPanel msgLabelPanel = new JPanel();
		    msgLabelPanel.setLayout(new BoxLayout(msgLabelPanel, BoxLayout.X_AXIS));
		    mainPanel.add(msgLabelPanel);
		    mainPanel.add(Box.createVerticalStrut(VERTICAL_GAP));
		    JPanel entryPanel = new JPanel();
		    entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.X_AXIS));
		    mainPanel.add(entryPanel);
		    mainPanel.add(Box.createVerticalStrut(VERTICAL_GAP));		    
		    JPanel buttonPanel = new JPanel();
		    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		    mainPanel.add(buttonPanel);
		    mainPanel.add(Box.createVerticalStrut(BORDER_WIDTH));

			msgLabel = new JLabel("Test");
		    msgLabelPanel.add(msgLabel);
		    msgLabelPanel.add(Box.createHorizontalGlue());
		    
		    typeLabel = new JLabel("[type]");
		    entryPanel.add(typeLabel);
		    entryPanel.add(Box.createHorizontalStrut(HORIZONTAL_GAP));
		    textField = new JTextField(TEXT_FIELD_COLUMNS);
		    entryPanel.add(textField);

		    buttonPanel.add(Box.createHorizontalGlue());
		    JButton okButton = new JButton("OK");
		    buttonPanel.add(okButton);
		    buttonPanel.add(Box.createHorizontalGlue());
		    okButton.setActionCommand("OK");
		    okButton.addActionListener(this);
		    
		}		
		
		private void prompt(String msg) {
			msgLabel.setText(msg);
			textField.setText("");
			confirmed = false;
			this.pack();
			setVisible(true);
		}
		
		public void promptForString(String msg) {
			typeLabel.setText("[String]");
			prompt(msg);
		}
		
		public void promptForInteger(String msg) {
			typeLabel.setText("[Integer]");
			prompt(msg);
		}
		
		public void promptForDouble(String msg) {
			typeLabel.setText("[Double]");
			prompt(msg);
		}
		
		public void promptForBoolean(String msg) {
			typeLabel.setText("[Boolean]");
			prompt(msg);
		}	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("OK")) {
				confirmed = true;
				setVisible(false);
			}
		}
		
		public boolean isConfirmed() {
			return confirmed;
		}

		public String getString() {
			return textField.getText();
		}
		
		public int getInteger() {
			Integer result = null; 
			try {
				result = Integer.valueOf(textField.getText());
			}
			catch(NumberFormatException exc) {
				result = 0;
			}
			return result;
		}
		
		public double getDouble() {
			Double result = null;
			
			try {
				result = Double.valueOf(textField.getText());
			}
			catch(NumberFormatException exc) {
				result = 0.0;
			}
			
			return result; 
		}
		
		public boolean getBoolean() {
			return Boolean.valueOf(textField.getText());
		}		
		
		private boolean confirmed = false;
		private JLabel typeLabel = null;
		private JLabel msgLabel = null;
		private JTextField textField = null;


	} 
	
	private class CallbackRecord {
		
		private Object object;
		private Method method;
		private String[] parameterLabels;
		
		public CallbackRecord(Object object, Method method, String ...parameterLabels) {
			this.object = object;
			this.method = method;
			this.parameterLabels = parameterLabels;
		}

		public Object getObject() {
			return object;
		}

		public Method getMethod() {
			return method;
		}

		public String[] getParameterLabels() {
			return parameterLabels;
		}
	}
		
	private static final long serialVersionUID = 1L;
	public GUInterface(String name) {
		this.setTitle(name);
		populateDialog();
		this.userInputDlg = new UserInputDlg(name);
		this.setVisible(true);
	}
	
	protected void displayIncomingSystemAction(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	/**
	 * Prompts the user for a String entry. 
	 * @param promptMsg the message displayed to the user
	 * @return the String entered by the user or null if the user has aborted the dialog
	 */
	protected String readString(String promptMsg) {
		userInputDlg.promptForString(promptMsg);
		if(userInputDlg.isConfirmed())
			return userInputDlg.getString();
		else
			return null;
	}
	
	/**
	 * Prompts the user for an Integer entry. 
	 * @param promptMsg the message displayed to the user
	 * @return the Integer entered by the user or null if the user has aborted the dialog
	 */
	protected Integer readInteger(String promptMsg) {
		userInputDlg.promptForInteger(promptMsg);
		if(userInputDlg.isConfirmed())
			return userInputDlg.getInteger();
		else
			return null;
	}

	/**
	 * Prompts the user for a Double entry. 
	 * @param promptMsg the message displayed to the user
	 * @return the Double entered by the user or null if the user has aborted the dialog
	 */
	protected Double readDouble(String promptMsg) {
		userInputDlg.promptForDouble(promptMsg);
		if(userInputDlg.isConfirmed())
			return userInputDlg.getDouble();
		else
			return null;
	}
	
	/**
	 * Prompts the user for a Boolean entry. 
	 * @param promptMsg the message displayed to the user
	 * @return the Boolean entered by the user or null if the user has aborted the dialog
	 */
	protected Boolean readBoolean(String promptMsg) {
		userInputDlg.promptForBoolean(promptMsg);
		if(userInputDlg.isConfirmed())
			return userInputDlg.getBoolean();
		else
			return null;
	}	
	
	
	private void populateDialog() {
	    Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(Box.createHorizontalStrut(BORDER_WIDTH));
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel);
		contentPane.add(Box.createHorizontalStrut(BORDER_WIDTH));
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(Box.createVerticalStrut(BORDER_WIDTH));
		JLabel buttonPanelLabel = new JLabel(BUTTONPANEL_LABEL);
		JPanel buttonPanelLabelPanel = new JPanel();
		buttonPanelLabelPanel.setLayout(new BoxLayout(buttonPanelLabelPanel, BoxLayout.X_AXIS));
		buttonPanelLabelPanel.add(buttonPanelLabel);
		buttonPanelLabelPanel.add(Box.createHorizontalGlue());
		mainPanel.add(buttonPanelLabelPanel);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));        
        buttonPanel.setPreferredSize(new Dimension(1, BUTTON_PANEL_HEIGHT));
		mainPanel.add(buttonPanel);
		mainPanel.add(Box.createVerticalStrut(VERTICAL_GAP));
		JLabel textAreaLabel = new JLabel(TEXTAREA_LABEL);
		JPanel textAreaLabelPanel = new JPanel();
		textAreaLabelPanel.setLayout(new BoxLayout(textAreaLabelPanel, BoxLayout.X_AXIS));
		textAreaLabelPanel.add(textAreaLabel);
		textAreaLabelPanel.add(Box.createHorizontalGlue());
		mainPanel.add(textAreaLabelPanel);
		textArea = new JTextArea(TEXTAREA_LINES, TEXTAREA_COLUMNS);
		JScrollPane textAreaScrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		mainPanel.add(textAreaScrollPane);
		mainPanel.add(Box.createVerticalStrut(BORDER_WIDTH));	
		this.pack();
		setResizable(false);
	}
	
	protected void addActorAction(String actorAction) {
		if(!actorActionButtons.containsKey(actorAction)) {
			JButton actorActionButton = new JButton(actorAction);
			actorActionButton.setActionCommand(actorAction);
			actorActionButton.addActionListener(this);
			actorActionButtons.put(actorAction, actorActionButton);
			buttonPanel.add(actorActionButton);
			this.pack();
		}
	}
	
	
	private static Method getMethod(Object obj, String methodName) {
		Method[] methods = obj.getClass().getDeclaredMethods();
		for(Method method : methods) {
			if(method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}
	
	protected void addActorAction(String actorAction, Object callbackObject, String callbackMethodName, String ...parameterLabels ) {
		Method callbackMethod = getMethod(callbackObject, callbackMethodName);
		if(callbackMethod != null) {
			CallbackRecord callbackRecord = new CallbackRecord(callbackObject,
					                                           callbackMethod, 
					                                           parameterLabels);
			callbackMap.put(actorAction, callbackRecord);
			addActorAction(actorAction);
		}
	}
	
	protected void addActorActions(Class<?> interfaceFromActor, Object objectImplementingInterface) {
		Method[] methods = interfaceFromActor.getMethods();
		for(Method method : methods) {
			addActorAction(method.getName(), objectImplementingInterface, method.getName());
		}
	}
		
	protected void removeActorAction(String actorAction) {
		if(actorActionButtons.containsKey(actorAction)) {
			JButton actorActionButton = actorActionButtons.get(actorAction);
			actorActionButton.removeActionListener(this);
			buttonPanel.remove(actorActionButton);
			this.pack();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actorActionButtons.containsKey(actionCommand))
			actorActionTriggered(actionCommand);
	}
	
	void actorActionTriggered(String actorAction) {
		CallbackRecord callbackRecord = callbackMap.get(actorAction);
		if(callbackRecord != null)
			performCallback(callbackRecord);
	}



	private void performCallback(CallbackRecord callbackRecord) {
		Object[] parameters = gatherParameters(callbackRecord.getMethod(), callbackRecord.getParameterLabels());
		if(parameters != null) {
			try {
				callbackRecord.getMethod().invoke(callbackRecord.getObject(), parameters);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}



	private Object[] gatherParameters(Method method, String[] parameterLabels) {
        Parameter[] parameterDefs = method.getParameters();
		Object[] parameters = new Object[parameterDefs.length];

		
		int idx = 0;
		for(Parameter parameterDef : parameterDefs) {
			Class<?> parameterType = parameterDef.getType();
			String parameterLabel = parameterDef.getName();
			Object parameterValue = null;
			
			if(idx < parameterLabels.length)
				parameterLabel = parameterLabels[idx];
			
			if(parameterType.equals(String.class)) {
				parameterValue = readString(parameterLabel);
			}
			else if(parameterType.equals(Integer.class)) {
				parameterValue = readInteger(parameterLabel);
			}
			else if(parameterType.equals(int.class)) {
				Integer intVal = readInteger(parameterLabel);
				if(intVal != null)
					parameterValue = intVal.intValue();
			}
			else if(parameterType.equals(Double.class)) {
				parameterValue = readDouble(parameterLabel);				
			}
			else if(parameterType.equals(double.class)) {
				Double doubleVal = readDouble(parameterLabel);
				if(doubleVal != null)
					parameterValue = doubleVal.doubleValue();				
			}
			
			if(parameterValue == null)
				return null;
			
			parameters[idx] = parameterValue;

			idx++;
		}
		
		return parameters;
	}



	private JTextArea textArea    = null;
	private JPanel    buttonPanel = null;
	private UserInputDlg userInputDlg = null;
	private HashMap<String, JButton> actorActionButtons = new HashMap<>();
	private HashMap<String, CallbackRecord> callbackMap = new HashMap<>();
	private static final int    BORDER_WIDTH        = 10;
	private static final int    VERTICAL_GAP        = 10;	
	private static final int    BUTTON_PANEL_HEIGHT = 160;
	private static final String BUTTONPANEL_LABEL   = "Actor Actions";
	private static final int    TEXTAREA_LINES      = 20;
	private static final int    TEXTAREA_COLUMNS    = 80;	
	private static final String TEXTAREA_LABEL      = "Incoming System Actions";
}
