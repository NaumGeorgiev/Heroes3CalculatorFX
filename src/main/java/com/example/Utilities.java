package com.example;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class Utilities {

        public static void setStart(Creature[] creatures, TextField armorerField, TextField offenseField,
                        TextField archeryField, TextField attackField, TextField defenceField,
                        TextField creatureCountField, ComboBox<String> attackerComboBox,
                        ComboBox<String> defenderComboBox, Image[] skillIcons, ImageView archeryImageView,
                        ImageView armorerImageView, ImageView offenseImageView, ToggleButton meleeButton,
                        ToggleButton shotsButton, RadioButton[] secondarySkillButtons, ToggleGroup[] toggleGroups) {
                setToNumeric(armorerField);
                setToNumeric(offenseField);
                setToNumeric(archeryField);
                setToNumeric(attackField);
                setToNumeric(defenceField);
                setToNumeric(creatureCountField);

                fill(attackerComboBox, creatures);
                fill(defenderComboBox, creatures);
                setFocuseUponClickingArrow(attackerComboBox);
                // addListener(attackerComboBox);

                attackerComboBox.getSelectionModel().select("Angel");
                defenderComboBox.getSelectionModel().select("Angel");
                setImageViewDefault(offenseImageView, skillIcons[0]);
                setImageViewDefault(archeryImageView, skillIcons[3]);
                setImageViewDefault(armorerImageView, skillIcons[6]);
                meleeButton.setVisible(false);
                shotsButton.setVisible(false);
                settingToggleGroups(secondarySkillButtons, toggleGroups);
        }
        private static void settingToggleGroups(RadioButton[] secondarySkillButtons, ToggleGroup[] toggleGroup){
                for(int i=0; i<secondarySkillButtons.length; i++){
                        secondarySkillButtons[i].setToggleGroup(toggleGroup[i/4]);
                }
        }

        private static void setImageViewDefault(ImageView skillImageView, Image basicSkillIcon) {
                skillImageView.setImage(basicSkillIcon);
                skillImageView.setOpacity(0.5);
        }

        private static void setFocuseUponClickingArrow(ComboBox<String> comboBox) {
                comboBox.setOnShowing(new EventHandler<Event>() {
                        @Override
                        public void handle(Event event) {
                                comboBox.requestFocus();
                        }
                });
        }
        // public static void addListener(ComboBox<String> comboBox) {
        // comboBox.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        // @Override
        // public void handle(KeyEvent keyEvent) {
        // setComboBoxText(keyEvent);
        // }
        // });
        // }
        // public static void setComboBoxText(KeyEvent keyEvent) {
        // System.out.println(keyEvent.getCode());

        // }

        private static void fill(ComboBox<String> comboBox, Creature[] creatures) {
                String[] temp = Creature.createNames(creatures);
                ObservableList<String> creatureNames = FXCollections.observableArrayList(temp);
                comboBox.setItems(creatureNames);
        }

        private static void setToNumeric(TextField textField) {
                textField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {
                                if (!newValue.matches("\\d*")) {
                                        textField.setText(newValue.replaceAll("[^\\d]", ""));
                                }
                        }
                });
        }

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

        public static RadioButton[] getFocusedToggleGroup(ToggleGroup offense, ToggleGroup armorer,
                        ToggleGroup archery) {
                ToggleGroup[] groups = { offense, armorer, archery };
                for (int i = 0; i < groups.length; i++) {
                        ObservableList<Toggle> radioButtonsList = groups[i].getToggles();
                        RadioButton[] radioButtons = radioButtonsList.toArray(new RadioButton[0]);
                        for (int j = 0; j < radioButtons.length; j++) {
                                if (((RadioButton) radioButtons[j]).isFocused()) {
                                        return radioButtons;
                                }
                        }
                }
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
        public static void attachEventHadnlers(RadioButton[] radioButtons){
                for(int i=0; i<radioButtons.length; i++){
                        radioButtons[i].setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.UP) {
                                
                            }
                        }
                    });
                }
        }

}
