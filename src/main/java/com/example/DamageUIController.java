package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
        private ImageView archeryImageView, armorerImageView, offenseImageView, blessImageView, curseImageView,
                        ringOfLifeImageView, ringOfVitalityImageView, vileOfLifebloodImageView, elixirOfLifImageView;
        @FXML
        private RadioButton noOffense, basicOffense, advancedOffense, expertOffense, noArmorer, basicArmorer,
                        advancedArmorer, expertArmorer, noArchery, basicArchery, advancedArchery, expertArchery,
                        advancedBless, advancedCurse, noSpellBuffs;
        @FXML
        private TextField offenseField, armorerField, archeryField, attackField, defenceField, creatureCountField,
                        joustingField;
        @FXML
        private ComboBox<String> attackerComboBox, defenderComboBox;
        @FXML
        private Button commitButton, swapButton, clearButton;
        @FXML
        private ToggleButton shotsButton, meleeButton;
        @FXML
        private Label label;
        @FXML
        CheckBox ringOfLife, ringOfVitality, vileOfLifeblood, elixirOfLife;

        public void initialize() {

                Utilities.setToNumeric(armorerField);
                Utilities.setToNumeric(offenseField);
                Utilities.setToNumeric(archeryField);
                Utilities.setToNumeric(attackField);
                Utilities.setToNumeric(defenceField);
                Utilities.setToNumeric(creatureCountField);
                Utilities.setToNumeric(joustingField);
                joustingField.setVisible(false);
                Utilities.addFilter(attackerComboBox, creatures);
                Utilities.addFilter(defenderComboBox, creatures);

                attackerComboBox.setValue("Zealot");
                defenderComboBox.setValue("Zealot");
                creatureCountField.setText("10");
                Utilities.setImageViewDefault(offenseImageView, skillsIcons[0]);
                Utilities.setImageViewDefault(archeryImageView, skillsIcons[3]);
                Utilities.setImageViewDefault(armorerImageView, skillsIcons[6]);
                Utilities.setImageViewDefault(blessImageView, new Image("Bless.png"));
                Utilities.setImageViewDefault(curseImageView, new Image("Curse.png"));
                Utilities.setImageViewDefault(ringOfLifeImageView, new Image("ringOfLife.gif"));
                Utilities.setImageViewDefault(ringOfVitalityImageView, new Image("ringOfVitality.gif"));
                Utilities.setImageViewDefault(vileOfLifebloodImageView, new Image("vileOfLifeblood.gif"));
                Utilities.setImageViewDefault(elixirOfLifImageView, new Image("elixirOfLife.gif"));

                meleeButton.setVisible(false);
                shotsButton.setVisible(false);
        }

        public void clear() {
                attackField.setText("");
                defenceField.setText("");
                offenseField.setText("");
                armorerField.setText("");
                archeryField.setText("");
                creatureCountField.setText("");
                attackerComboBox.getSelectionModel().select(null);
                defenderComboBox.getSelectionModel().select(null);
                attackerComboBox.hide();
                defenderComboBox.hide();
                noOffense.setSelected(true);
                noArchery.setSelected(true);
                noArmorer.setSelected(true);
                offenseImageView.setImage(skillsIcons[0]);
                archeryImageView.setImage(skillsIcons[3]);
                armorerImageView.setImage(skillsIcons[6]);
                offenseImageView.setOpacity(0.5);
                armorerImageView.setOpacity(0.5);
                archeryImageView.setOpacity(0.5);
                noSpellBuffs.setSelected(true);
                elixirOfLife.setSelected(false);
                ringOfLife.setSelected(false);
                ringOfVitality.setSelected(false);
                vileOfLifeblood.setSelected(false);
                elixirOfLifImageView.setOpacity(0.5);
                ringOfLifeImageView.setOpacity(0.5);
                ringOfVitalityImageView.setOpacity(0.5);
                vileOfLifebloodImageView.setOpacity(0.5);
                label.setText("");
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
        public void setSpellBuffIcon() {
                Utilities.setSpellBuffsImageView(blessImageView, curseImageView, advancedBless, advancedCurse,
                                noSpellBuffs);
        }
        @FXML
        public void setArtifactsIcons() {
                Utilities.setArtifactsImageView(ringOfLifeImageView, ringOfVitalityImageView, vileOfLifebloodImageView,
                                elixirOfLifImageView, ringOfLife, ringOfVitality, vileOfLifeblood, elixirOfLife);
        }

        @FXML
        public void setArcherySkillIcon() {
                Utilities.setSkillImageView(archeryImageView, noArchery, basicArchery, advancedArchery, expertArchery,
                                skillsIcons[3], skillsIcons[4], skillsIcons[5]);
        }

        @FXML
        public void setArmorerSkillIcon() {
                Utilities.setSkillImageView(armorerImageView, noArmorer, basicArmorer, advancedArmorer, expertArmorer,
                                skillsIcons[6], skillsIcons[7], skillsIcons[8]);
        }

        @FXML
        public void setOffenseSkillIcon() {
                Utilities.setSkillImageView(offenseImageView, noOffense, basicOffense, advancedOffense, expertOffense,
                                skillsIcons[0], skillsIcons[1], skillsIcons[2]);
        }
        @FXML
        public void literallyNothing(){

        }
        @FXML
        public void setMeleeShotSumJoustingVisibility() {
                if (attackerComboBox.getSelectionModel().getSelectedItem()!=null && !attackerComboBox.getSelectionModel().getSelectedItem().equals("")) {
                        meleeButton.setSelected(false);
                        shotsButton.setSelected(false);
                        joustingField.setText("");
                        joustingField.setVisible(false);
                        Creature creature = Creature.getCreatureByName(creatures,
                                        attackerComboBox.getSelectionModel().getSelectedItem());
                        if (creature != null && creature.isRanged) {
                                meleeButton.setVisible(true);
                                shotsButton.setVisible(true);
                        } else {
                                meleeButton.setVisible(false);
                                shotsButton.setVisible(false);
                        }
                        if (creature.name.equals("Champion") || creature.name.equals("Cavalier"))
                                joustingField.setVisible(true);
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
        public void swap() {

                String attacker = attackerComboBox.getSelectionModel().getSelectedItem();
                String defender = defenderComboBox.getSelectionModel().getSelectedItem();
                clear();
                attackerComboBox.getSelectionModel().select(defender);
                defenderComboBox.getSelectionModel().select(attacker);
        }

        @FXML
        public void displayDamage() {
                String attackerName = attackerComboBox.getValue();
                String defenderName = defenderComboBox.getValue();
                if (attackerName == null)
                        attackerName = "";
                if (defenderName == null)
                        defenderName = "";
                Creature defender = Creature.getCreatureByName(creatures, defenderName);
                Creature attacker = Creature.getCreatureByName(creatures, attackerName);
                if (defender == null || attacker == null || creatureCountField.getText().isEmpty())
                        Utilities.setIncorrectInputLabel(label);
                else {
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
                        int joustingSteps = Utilities.getTextFieldNumberValue(joustingField);
                        int creatureCount = Utilities.getTextFieldNumberValue(creatureCountField);
                        boolean isAdvancedBlessed = advancedBless.isSelected();
                        boolean isAdvancedCursed = advancedCurse.isSelected();

                        DamageCalculator damageCalculator = new DamageCalculator(attacker.name, defender.name, attack,
                                        defence,
                                        armorer, offence, archery, offenseHeroLevel, archeryHeroLevel, armorerHeroLevel,
                                        isRanged, minDamage, maxDamage, creatureCount);
                        int[] damage = damageCalculator.calculate(attacker, defender, shotsButton.isSelected(),
                                        isAdvancedBlessed, isAdvancedCursed, joustingSteps);
                        int health = Utilities.getHealth(defender, ringOfLife.isSelected(), ringOfVitality.isSelected(),
                                        vileOfLifeblood.isSelected(), elixirOfLife.isSelected());
                        Utilities.setDamageLabel(label, damage, health);

                }
        }

}
