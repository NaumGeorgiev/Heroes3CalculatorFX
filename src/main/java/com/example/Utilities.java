package com.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

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

        public static void setImageViewDefault(ImageView imageView, Image image) {
                imageView.setImage(image);
                imageView.setOpacity(0.5);
        }

        public static void setToNumeric(TextField textField) {
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
        public static int getHealth(Creature creature, boolean ringOfLife, boolean ringOfVitality, boolean vileOfLifeblood, boolean elixirOfLife){
                int health=creature.health;
                if(elixirOfLife && !creature.isElixirImmune){
                        return (health+4)*125/100;
                }
                if(ringOfLife)
                health++;
                if(ringOfVitality)
                health++;
                if(vileOfLifeblood)
                health+=2;
                return health;
        }
        public static void setDamageLabel(Label label, int[] damage, int health) {
                int averageDamage = (damage[0] + damage[1]) / 2;
                label.setFont(new Font(28));
                if (damage[0] == damage[1])
                        label.setText(averageDamage + "\n" + averageDamage / health + "+" + averageDamage % health
                                        + " HP");
                else
                        label.setText(damage[0] + "-" + damage[1] + " | " + averageDamage + " on average\n"
                                        + damage[0] / health + "+" + damage[0] % health + " HP - " + damage[1] / health
                                        + "+"
                                        + damage[1] % health + " HP\n" + (averageDamage / health) + "+"
                                        + averageDamage % health + " HP on average");
        }

        public static void setIncorrectInputLabel(Label label) {
                label.setFont(new Font(28));
                label.setText("Invalid input");

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


        public static void setArtifactsImageView(ImageView ringOfLifeImageView, ImageView ringOfVitalityImageView,
                        ImageView vileOfLifebloodImageView, ImageView elixirOfLifeImageView, CheckBox ringOfLife,
                        CheckBox ringOfVitality, CheckBox vileOfLifeblood, CheckBox elixirOfLife) {

                if (elixirOfLife.isSelected() && elixirOfLifeImageView.getOpacity() == 0.5) {
                        ringOfLife.setSelected(true);
                        ringOfVitality.setSelected(true);
                        vileOfLifeblood.setSelected(true);
                        elixirOfLifeImageView.setOpacity(1);
                } else {
                        elixirOfLife.setSelected(false);
                        elixirOfLifeImageView.setOpacity(0.5);
                }
                if (ringOfLife.isSelected())
                        ringOfLifeImageView.setOpacity(1);
                else
                        ringOfLifeImageView.setOpacity(0.5);
                if (ringOfVitality.isSelected())
                        ringOfVitalityImageView.setOpacity(1);
                else
                        ringOfVitalityImageView.setOpacity(0.5);
                if (vileOfLifeblood.isSelected())
                        vileOfLifebloodImageView.setOpacity(1);
                else
                        vileOfLifebloodImageView.setOpacity(0.5);

        }

        public static void setSpellBuffsImageView(ImageView blessImageView, ImageView curseImageView,
                        RadioButton advancedBless, RadioButton advancedCurse, RadioButton noSpellBuff) {
                if (advancedBless.isSelected()) {
                        curseImageView.setOpacity(0.5);
                        blessImageView.setOpacity(1);
                } else if (advancedCurse.isSelected()) {
                        blessImageView.setOpacity(0.5);
                        curseImageView.setOpacity(1);
                } else if (noSpellBuff.isSelected()) {
                        blessImageView.setOpacity(0.5);
                        curseImageView.setOpacity(0.5);
                }
        }

        public static void setSkillImageView(ImageView skillImageView, RadioButton no, RadioButton basic,
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

        public static void addFilter(ComboBox<String> comboBox, Creature[] creatures) {
                ObservableList<String> items = FXCollections.observableArrayList(Creature.createNames(creatures));

                comboBox.setItems(items);

                comboBox.setOnKeyReleased(new EventHandler<KeyEvent>() {

                        @Override
                        public void handle(KeyEvent event) {
                                comboBox.show();
                                if (event.getCode() == KeyCode.ESCAPE) {
                                        comboBox.getEditor().setText("");
                                        comboBox.hide();
                                        return;
                                }
                                if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP)
                                        return;
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
