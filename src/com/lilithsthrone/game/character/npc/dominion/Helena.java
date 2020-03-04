package com.lilithsthrone.game.character.npc.dominion;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.3.7
 * @author Innoxia
 */
public class Helena extends NPC {

	public Helena() {
		this(false);
	}
	
	public Helena(boolean isImported) {
		super(isImported, new NameTriplet("Helena"), "Labelle",
				"Helena is an extremely powerful harpy matriarch, and is in control of one of the largest harpy flocks in Dominion."
						+ " Her beauty rivals that of even the most gorgeous of succubi, which, combined with her sharp mind and regal personality, makes her somewhat of an idol in harpy society.",
				26, Month.MAY, 3,
				10, Gender.F_V_B_FEMALE, Subspecies.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
		
		if(!isImported) {
			this.addSpell(Spell.SLAM);
			this.addSpell(Spell.ARCANE_AROUSAL);
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 5);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(10);
			this.resetPerksMap(true);
			this.addSpell(Spell.SLAM);
			this.addSpell(Spell.ARCANE_AROUSAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.INNOCENT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.setName(new NameTriplet("Helena"));
			this.getHomeCell().removeCharacterHomeId("-1,Alexa");
			this.getHomeCell().addCharacterHomeId("-1,Helena");
			this.getCell().removeCharacterPresentId("-1,Alexa");
			this.getCell().addCharacterPresentId("-1,Helena");
			this.setId("-1,Helena");
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.2")) {
			this.setSurname("Labelle");
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.9")) {
			this.equipClothing();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_PURE_VIRGIN);
			this.resetPerksMap(true, false);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_PALE), true);
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_BLUE_LIGHT));
			this.setHairStyle(HairStyle.STRAIGHT);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.INNOCENT);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_HARPY_MATRIARCH);
			
			this.addFetish(Fetish.FETISH_PURE_VIRGIN);
		}
		
		// Body:

		// Core:
		this.setHeight(162);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_BLUE_LIGHT));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_PALE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, Colour.COVERING_WHITE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.COVERING_WHITE), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.STRAIGHT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HARPY, Colour.COVERING_WHITE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_CLEAR));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.D.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.setPiercedEar(true);
		this.setPiercedNavel(false);
		this.setPiercedNose(false);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_piercing_ear_hoops", Colour.CLOTHING_GOLD, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_PLUNGE_DRESS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_head_tiara", Colour.CLOTHING_GOLD, false), true, this);
//		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_GOLD, false), true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#59005C";
		} else {
			return "#FFDFB3";
		}
	}
	
	@Override
	public void dailyUpdate() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, false);
		
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY)) {
			for(String id : this.getSlavesOwned()) {
				if(Main.game.isCharacterExisting(id)) {
					Main.game.banishNPC(id);
				}
			}
			for(GameCharacter character : new ArrayList<>(Main.game.getCharactersPresent(this.getCell()))) { // Catch for old version which had bugged slaves standing on Helena's tile:
				if(character.isSlave() && !character.getOwner().isPlayer() && character instanceof DominionAlleywayAttacker) {
					Main.game.banishNPC((NPC) character);
				}
			}
			this.removeAllSlaves();
			
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
				for(int i=0; i<2; i++) {
					NPC newSlave = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false), false, NPCGenerationFlag.NO_CLOTHING_EQUIP);
					newSlave.setHistory(Occupation.NPC_SLAVE);
					try {
						Main.game.addNPC(newSlave, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					newSlave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
					addSlave(newSlave);
					newSlave.resetInventory(true);
					newSlave.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_bdsm_metal_collar", Colour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Helena.class));
					newSlave.setPlayerKnowsName(true);
				}
			}
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if((Main.game.getPlayer().hasQuest(QuestLine.MAIN) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA))
					|| Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				if(Main.game.isExtendedWorkTime()) {
					this.setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
					
				} else {
					this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
				}
				
			} else {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)
						&& ((Main.game.isWorkTime() && Main.game.getDateNow().getDayOfWeek()!=DayOfWeek.SATURDAY && Main.game.getDateNow().getDayOfWeek()!=DayOfWeek.SUNDAY) // Helena doesn't work over the weekend
							|| Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE
							|| (Main.game.getDateNow().getDayOfWeek()==DayOfWeek.FRIDAY && Main.game.getHourOfDay()>12 && Main.game.getHourOfDay()<21))) { // Helena works late on Fridays
					this.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
					
				} else {
					this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
					
					if(!this.getSlavesOwned().isEmpty() && !Main.game.isWorkTime() && Main.game.getHourOfDay()>12) {
						sellOffRemainingSlaves();
					}
				}
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	public void sellOffRemainingSlaves() {
		for(String id : this.getSlavesOwned()) {
			if(Main.game.isCharacterExisting(id)) {
				Main.game.banishNPC(id);
			}
		}
		this.removeAllSlaves();
	}

}