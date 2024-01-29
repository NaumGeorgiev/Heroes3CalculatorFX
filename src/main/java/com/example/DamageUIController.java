package com.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DamageUIController {
        private static Creature[] creatures = Creature.createAll();
        private Image[] skillsIcons = Utilities.getSkillIcons();
        private String attackerSearchString=new String("");
        @FXML
        private ImageView archeryImageView, armorerImageView, offenseImageView;
        @FXML
        private RadioButton noOffense, basicOffense, advancedOffense, expertOffense, noArmorer, basicArmorer,
                        advancedArmorer, expertArmorer, noArchery, basicArchery, advancedArchery, expertArchery;
        @FXML
        private TextField offenseField, armorerField, archeryField, attackField, defenceField, creatureCountField;
        @FXML
        private ComboBox<String> attackerComboBox, defenderComboBox;
        @FXML
        private Button commitButton, swapButton;
        @FXML
        private ToggleButton shotsButton, meleeButton;
        @FXML
        private Label label;
        // private ToggleGroup offenseGroup = new ToggleGroup();
        // private ToggleGroup armorerGroup = new ToggleGroup();
        // private ToggleGroup archeryGroup = new ToggleGroup();

        public void initialize() {
                // ToggleGroup[] toggleGroups = toggleGroupsToArray(offenseGroup, armorerGroup,
                // archeryGroup);
                // RadioButton[] secondarySkillButtons = radioButtonsToArray(noOffense,
                // basicOffense, advancedOffense,
                // expertOffense, noArmorer, basicArmorer, advancedArmorer, expertArmorer,
                // noArchery,
                // basicArchery, advancedArchery, expertArchery);
                setToNumeric(armorerField);
                setToNumeric(offenseField);
                setToNumeric(archeryField);
                setToNumeric(attackField);
                setToNumeric(defenceField);
                setToNumeric(creatureCountField);
                fill(attackerComboBox, creatures);
                fill(defenderComboBox, creatures);
                // setFocuseUponClickingArrow(attackerComboBox);

                attackerComboBox.getSelectionModel().select("Angel");
                defenderComboBox.getSelectionModel().select("Angel");
                setImageViewDefault(offenseImageView, skillsIcons[0]);
                setImageViewDefault(archeryImageView, skillsIcons[3]);
                setImageViewDefault(armorerImageView, skillsIcons[6]);
                meleeButton.setVisible(false);
                shotsButton.setVisible(false);
                // settingToggleGroups(secondarySkillButtons, toggleGroups);
        }
        // private static void settingToggleGroups(RadioButton[] secondarySkillButtons,
        // ToggleGroup[] toggleGroup){
        // for(int i=0; i<secondarySkillButtons.length; i++){
        // secondarySkillButtons[i].setToggleGroup(toggleGroup[i/4]);
        // }
        // }

        private static void setImageViewDefault(ImageView skillImageView, Image basicSkillIcon) {
                skillImageView.setImage(basicSkillIcon);
                skillImageView.setOpacity(0.5);
        }

        // private static void setFocuseUponClickingArrow(ComboBox<String> comboBox) {
        //         comboBox.setOnShowing(new EventHandler<Event>() {
        //                 @Override
        //                 public void handle(Event event) {
        //                         comboBox.requestFocus();
        //                 }
        //         });
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
        // private static ToggleGroup[] toggleGroupsToArray(ToggleGroup... toggleGroups)
        // {
        // return toggleGroups;
        // }

        // private static RadioButton[] radioButtonsToArray(RadioButton... radioButtons)
        // {
        // return radioButtons;
        // }
        @FXML
        public void filterComboBox(){
                
        }
        @FXML
        public void clearField(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        if (offenseField.isFocused())
                                offenseField.clear();
                        else if (armorerField.isFocused())
                                armorerField.clear();
                        else if (archeryField.isFocused())
                                archeryField.clear();
                        else if (attackField.isFocused())
                                attackField.clear();
                        else if (defenceField.isFocused())
                                defenceField.clear();
                        else if (creatureCountField.isFocused())
                                creatureCountField.clear();
                }
        }

        @FXML
        public void setArcherySkillIcon() {
                Utilities.setImageView(archeryImageView, noArchery, basicArchery, advancedArchery, expertArchery,
                                skillsIcons[3], skillsIcons[4], skillsIcons[5]);
        }

        @FXML
        public void setArmorerSkillIcon() {
                Utilities.setImageView(armorerImageView, noArmorer, basicArmorer, advancedArmorer, expertArmorer,
                                skillsIcons[6], skillsIcons[7], skillsIcons[8]);
        }

        @FXML
        public void setOffenseSkillIcon() {
                Utilities.setImageView(offenseImageView, noOffense, basicOffense, advancedOffense, expertOffense,
                                skillsIcons[0], skillsIcons[1], skillsIcons[2]);
        }

        @FXML
        public void setMeleeShotSumVisibility() {
                if (attackerComboBox.getSelectionModel().getSelectedItem() != null) {
                        meleeButton.setSelected(false);
                        shotsButton.setSelected(false);
                        if (Creature.getCreatureByName(creatures,
                                        attackerComboBox.getSelectionModel().getSelectedItem()).isRanged) {
                                meleeButton.setVisible(true);
                                shotsButton.setVisible(true);
                        } else {
                                meleeButton.setVisible(false);
                                shotsButton.setVisible(false);
                        }
                }
        }

        // @FXML
        // public void setToOffenseGroup(KeyEvent keyEvent) {
        // if (keyEvent.getCode() == KeyCode.UP) {
        // if (expertArmorer.isSelected())
        // noArmorer.setSelected(true);
        // else if (advancedArmorer.isSelected())
        // expertArmorer.setSelected(true);
        // else if (basicArmorer.isSelected())
        // advancedArmorer.setSelected(true);
        // else if (noArmorer.isSelected())
        // basicArmorer.setSelected(true);

        // }
        // Utilities.getSelectedRadioButton(noOffense, basicOffense, advancedOffense,
        // expertOffense)
        // .requestFocus();
        // keyEvent.consume();
        // }

        // @FXML
        // public void setSKillLevel(KeyEvent keyEvent) {
        // Utilities.attachEventHadnlers(radioButtonsToArray(noOffense, basicOffense,
        // advancedOffense,
        // expertOffense, noArmorer, basicArmorer, advancedArmorer, expertArmorer,
        // noArchery,
        // basicArchery, advancedArchery, expertArchery));
        // if (keyEvent.getCode().isArrowKey()) {
        // // if (keyEvent.getCode() == KeyCode.UP) {
        // // RadioButton[] radioButtons = Utilities.getFocusedToggleGroup(offenseGroup,
        // armorerGroup,
        // // archeryGroup);

        // // }
        // setArcherySkillIcon();
        // setArmorerSkillIcon();
        // setOffenseSkillIcon();
        // }
        // if(keyEvent.getCode().isDigitKey()){
        // RadioButton[] radioButtons = Utilities.getFocusedToggleGroup(offneseGroup,
        // armorerGroup, archeryGroup);
        // if (keyEvent.getCode() == KeyCode.DIGIT1) {
        // radioButtons[0].setSelected(true);
        // radioButtons[0].requestFocus();
        // }
        // else if (keyEvent.getCode() == KeyCode.DIGIT2) {
        // radioButtons[1].setSelected(true);
        // radioButtons[1].requestFocus();
        // }
        // else if (keyEvent.getCode() == KeyCode.DIGIT3) {
        // radioButtons[2].setSelected(true);
        // radioButtons[2].requestFocus();
        // }
        // else if (keyEvent.getCode() == KeyCode.DIGIT4) {
        // radioButtons[3].setSelected(true);
        // radioButtons[3].requestFocus();
        // }
        // setArcherySkillIcon();
        // setArmorerSkillIcon();
        // setOffenseSkillIcon();
        // }
        // }

        // @FXML
        // public void SelectComponent(KeyEvent keyEvent) {
        // System.out.println(keyEvent.getCode());
        // if (keyEvent.getCode() == KeyCode.F1)
        // Utilities.getSelectedRadioButton(noOffense, basicOffense, advancedOffense,
        // expertOffense)
        // .requestFocus();
        // else if (keyEvent.getCode() == KeyCode.F2)
        // Utilities.getSelectedRadioButton(noArmorer, basicArmorer, advancedArmorer,
        // expertArmorer)
        // .requestFocus();
        // else if (keyEvent.getCode() == KeyCode.F3)
        // Utilities.getSelectedRadioButton(noArchery, basicArchery, advancedArchery,
        // expertArchery)
        // .requestFocus();
        // }

        @FXML
        public void setMeleeNotSelected() {
                meleeButton.setSelected(false);
        }

        @FXML
        public void setShotSumNotSelected() {
                shotsButton.setSelected(false);
        }

        @FXML
        public void displayDamage() {
                String attackerName = attackerComboBox.getValue();
                String defenderName = defenderComboBox.getValue();
                Creature defender = Creature.getCreatureByName(creatures, defenderName);
                Creature attacker = Creature.getCreatureByName(creatures, attackerName);
                double minDamage = attacker.minDamage;
                double maxDamage = attacker.maxDamage;
                boolean isRanged = attacker.isRanged;
                if (meleeButton.isSelected())
                        isRanged = false;
                int offence = Utilities.getSecondarySkillLevel(noOffense, basicOffense, advancedOffense, expertOffense);
                int armorer = Utilities.getSecondarySkillLevel(noArmorer, basicArmorer, advancedArmorer, expertArmorer);
                int archery = Utilities.getSecondarySkillLevel(noArchery, basicArchery, advancedArchery, expertArchery);
                int offenseHeroLevel = Utilities.getTextFieldNumberValue(offenseField);
                int armorerHeroLevel = Utilities.getTextFieldNumberValue(armorerField);
                int archeryHeroLevel = Utilities.getTextFieldNumberValue(archeryField);
                int attack = Utilities.getTextFieldNumberValue(attackField) + attacker.attack;
                int defence = Utilities.getTextFieldNumberValue(defenceField) + defender.defence;
                int creatureCount = Utilities.getTextFieldNumberValue(creatureCountField);

                DamageCalculator damageCalculator = new DamageCalculator(attackerName, defenderName, attack, defence,
                                armorer, offence, archery, offenseHeroLevel, archeryHeroLevel, armorerHeroLevel,
                                isRanged, minDamage, maxDamage, creatureCount);
                int[] damage = damageCalculator.calculate(attacker, defender, shotsButton.isSelected());
                Utilities.setLabel(label, damage);

        }

}
