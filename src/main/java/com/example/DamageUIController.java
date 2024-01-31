package com.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

        public void initialize() {

                setToNumeric(armorerField);
                setToNumeric(offenseField);
                setToNumeric(archeryField);
                setToNumeric(attackField);
                setToNumeric(defenceField);
                setToNumeric(creatureCountField);
                Utilities.addFilter(attackerComboBox, creatures);
                Utilities.fill(defenderComboBox, creatures);

                // attackerComboBox.getSelectionModel().select("Angel");
                defenderComboBox.getSelectionModel().select("Angel");
                setImageViewDefault(offenseImageView, skillsIcons[0]);
                setImageViewDefault(archeryImageView, skillsIcons[3]);
                setImageViewDefault(armorerImageView, skillsIcons[6]);
                meleeButton.setVisible(false);
                shotsButton.setVisible(false);
        }


        private static void setImageViewDefault(ImageView skillImageView, Image basicSkillIcon) {
                skillImageView.setImage(basicSkillIcon);
                skillImageView.setOpacity(0.5);
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
        
        @FXML
        public void filterComboBox() {

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
                        Creature creature=Creature.getCreatureByName(creatures,
                        attackerComboBox.getSelectionModel().getSelectedItem());
                        if (creature!=null && creature.isRanged) {
                                meleeButton.setVisible(true);
                                shotsButton.setVisible(true);
                        } else {
                                meleeButton.setVisible(false);
                                shotsButton.setVisible(false);
                        }
                }
        }

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
                String attackerName=attackerComboBox.getValue();
                String defenderName=attackerComboBox.getValue();
                if(attackerName==null)
                attackerName="";
                if(defenderName==null)
                defenderName="";
                Creature defender = Creature.getCreatureByName(creatures, defenderName);
                Creature attacker = Creature.getCreatureByName(creatures, attackerName);
                if(defender==null || attacker==null || creatureCountField.getText().isEmpty())
                label.setText("Insert correct input");
                else{
                        double minDamage = attacker.minDamage;
                        double maxDamage = attacker.maxDamage;
                        boolean isRanged = attacker.isRanged;
                        if (meleeButton.isSelected())
                                isRanged = false;
                        int offence = Utilities.getSecondarySkillLevel(noOffense, basicOffense, advancedOffense,
                                        expertOffense);
                        int armorer = Utilities.getSecondarySkillLevel(noArmorer, basicArmorer, advancedArmorer,
                                        expertArmorer);
                        int archery = Utilities.getSecondarySkillLevel(noArchery, basicArchery, advancedArchery,
                                        expertArchery);
                        int offenseHeroLevel = Utilities.getTextFieldNumberValue(offenseField);
                        int armorerHeroLevel = Utilities.getTextFieldNumberValue(armorerField);
                        int archeryHeroLevel = Utilities.getTextFieldNumberValue(archeryField);
                        int attack = Utilities.getTextFieldNumberValue(attackField) + attacker.attack;
                        int defence = Utilities.getTextFieldNumberValue(defenceField) + defender.defence;
                        int creatureCount = Utilities.getTextFieldNumberValue(creatureCountField);

                        DamageCalculator damageCalculator = new DamageCalculator(attacker.name, defender.name, attack,
                                        defence,
                                        armorer, offence, archery, offenseHeroLevel, archeryHeroLevel, armorerHeroLevel,
                                        isRanged, minDamage, maxDamage, creatureCount);
                        int[] damage = damageCalculator.calculate(attacker, defender, shotsButton.isSelected());
                        Utilities.setLabel(label, damage);

                }
                }

        }

