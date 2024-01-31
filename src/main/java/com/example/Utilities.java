package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Utilities {


        public static int getSecondarySkillLevel(RadioButton noSkill, RadioButton basicSkill, RadioButton advancedSkill,
                        RadioButton expertSkill) {
                if (noSkill.isSelected())
                        return 0;
                if (basicSkill.isSelected())
                        return 1;
                if (advancedSkill.isSelected())
                        return 2;
                if (expertSkill.isSelected())
                        return 3;
                return 69;
        }

        public static RadioButton getSelectedRadioButton(RadioButton noSkill, RadioButton basicSkill,
                        RadioButton advancedSkill, RadioButton expertSkill) {
                if (noSkill.isSelected())
                        return noSkill;
                if (basicSkill.isSelected())
                        return basicSkill;
                if (advancedSkill.isSelected())
                        return advancedSkill;
                if (expertSkill.isSelected())
                        return expertSkill;
                return null;
        }


        public static int getTextFieldNumberValue(TextField textField) {
                if (textField.getText().isEmpty())
                        return 0;
                return Integer.parseInt(textField.getText());
        }

        public static void setLabel(Label label, int[] damage) {
                label.setText(damage[0] + " " + damage[1]);
        }

        public static Image[] getSkillIcons() {
                Image[] skillIcons = new Image[9];
                skillIcons[0] = new Image("BasicOffense.png");
                skillIcons[1] = new Image("AdvancedOffense.png");
                skillIcons[2] = new Image("ExpertOffense.png");

                skillIcons[3] = new Image("BasicArchery.png");
                skillIcons[4] = new Image("AdvancedArchery.png");
                skillIcons[5] = new Image("ExpertArchery.png");

                skillIcons[6] = new Image("BasicArmorer.png");
                skillIcons[7] = new Image("AdvancedArmorer.png");
                skillIcons[8] = new Image("ExpertArmorer.png");

                return skillIcons;

        }

        public static void setImageView(ImageView skillImageView, RadioButton no, RadioButton basic,
                        RadioButton advanced, RadioButton expert, Image basicIcon, Image advancedIcon,
                        Image ExpertIcon) {
                if (no.isSelected()) {
                        skillImageView.setOpacity(0.5);
                        skillImageView.setImage(basicIcon);
                } else {
                        skillImageView.setOpacity(1);
                        if (basic.isSelected())
                                skillImageView.setImage(basicIcon);
                        else if (advanced.isSelected())
                                skillImageView.setImage(advancedIcon);
                        else if (expert.isSelected())
                                skillImageView.setImage(ExpertIcon);
                }
        }
        
        public static void fill(ComboBox<String> comboBox, Creature[] creatures) {
                String[] temp = Creature.createNames(creatures);
                ObservableList<String> creatureNames = FXCollections.observableArrayList(temp);
                comboBox.setItems(creatureNames);
        }

        public static void addFilter(ComboBox<String> comboBox, Creature[] creatures) {
                ObservableList<String> items = FXCollections.observableArrayList(Creature.createNames(creatures));
        
                comboBox.setItems(items);
        
                comboBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() ==KeyCode.CONTROL){
                                comboBox.show();
                        }
                        if(event.getCode()==KeyCode.ESCAPE){
                                comboBox.getEditor().setText("");
                        }
                        String input = comboBox.getEditor().getText();
        
                        ObservableList<String> filteredItems = FXCollections.observableArrayList();
        
                        for (String item : items) {
                            if (item.toLowerCase().contains(input.toLowerCase())) {
                                filteredItems.add(item);
                            }
                        }
        
                        comboBox.setItems(filteredItems);
                    }
                });
            }
        }

