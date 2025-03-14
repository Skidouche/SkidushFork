package net.lepidodendron.util;

import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.*;
import net.lepidodendron.item.*;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

public class AcidBathOutputPlants {

    public static String resLocPlants (int dim) {

        switch (dim) {
            case 1:
            default: //Precambrian
                if (!(getPrecambrianCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                int ii = (new Random()).nextInt(getPrecambrianCleanedFossilsPlants().length);
                return getPrecambrianCleanedFossilsPlants()[ii];

            case 2: //Cambrian
                if (!(getCambrianCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getCambrianCleanedFossilsPlants().length);
                return getCambrianCleanedFossilsPlants()[ii];

            case 3: //Ordovician
                if (!(getOrdovicianCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getOrdovicianCleanedFossilsPlants().length);
                return getOrdovicianCleanedFossilsPlants()[ii];

            case 4: //Silurian
                if (!(getSilurianCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getSilurianCleanedFossilsPlants().length);
                return getSilurianCleanedFossilsPlants()[ii];

            case 5: //Devonian
                if (!(getDevonianCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getDevonianCleanedFossilsPlants().length);
                return getDevonianCleanedFossilsPlants()[ii];

            case 6: //Carboniferous
                if (!(getCarboniferousCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getCarboniferousCleanedFossilsPlants().length);
                return getCarboniferousCleanedFossilsPlants()[ii];

            case 7: //Permian
                if (!(getPermianCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getPermianCleanedFossilsPlants().length);
                return getPermianCleanedFossilsPlants()[ii];

            case 8: //Triassic
                if (!(getTriassicCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getTriassicCleanedFossilsPlants().length);
                return getTriassicCleanedFossilsPlants()[ii];

            case 9: //Jurassic
                if (!(getJurassicCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getJurassicCleanedFossilsPlants().length);
                return getJurassicCleanedFossilsPlants()[ii];

            case 10: //Cretaceous
                if (!(getCretaceousCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getCretaceousCleanedFossilsPlants().length);
                return getCretaceousCleanedFossilsPlants()[ii];

            case 11: //Paleogene
                if (!(getPaleogeneCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getPaleogeneCleanedFossilsPlants().length);
                return getPaleogeneCleanedFossilsPlants()[ii];

            case 12: //Neogene
                if (!(getNeogeneCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getNeogeneCleanedFossilsPlants().length);
                return getNeogeneCleanedFossilsPlants()[ii];

            case 13: //Pleistocene
                if (!(getPleistoceneCleanedFossilsPlants().length >= 1)) {
                    return "";
                }
                ii = (new Random()).nextInt(getPleistoceneCleanedFossilsPlants().length);
                return getPleistoceneCleanedFossilsPlants()[ii];
        }
    }

    public static String[] getPrecambrianCleanedFossilsPlants() {
        String[] resLoc = LepidodendronConfig.revPlantsPrecambrian;
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsPrecambrian);
        return resLoc;
    }

    public static String[] getCambrianCleanedFossilsPlants() {
        String[] resLoc = LepidodendronConfig.revPlantsCambrian;
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsCambrian);
        return resLoc;
    }

    public static String[] getOrdovicianCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockDollyphyton.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEdwardsiphyton.block).toString()
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsOrdovician);
        return resLoc;
    }

    public static String[] getSilurianCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.ITEMS.getKey(ItemBaragwanathiaItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPrototaxites.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNematophyta.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZosterophyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString()
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsSilurian);
        return resLoc;
    }

    public static String[] getDevonianCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAdoketophyton.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAneurophytonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockArchaeopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAsteroxylon.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemBaragwanathiaItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCalamophytonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCooksonia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockElkinsia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFlabellopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFoozia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGuangdedendron.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockIbyka.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockKeraphyton.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLeclercqia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLepidosigillaria.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOmprelostrobus.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPertica.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProtolepidodendropsisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPrototaxites.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNematophyta.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPietzschia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPseudobornia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPsilophyton.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRellimia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRhacophyton.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemRhyniaItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSigillariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockStauropteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSublepidodendron.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTetraxylopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWattiezaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockXenocladiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockXihuphyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZosterophyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString()
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsDevonian);
        return resLoc;
    }

    public static String[] getCarboniferousCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAlethopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAlliopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAnkyropterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBothrodendronSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemBumbudendronItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCalamitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCallistophytales.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCecropsis.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCordaitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDiaphorodendronSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicranophyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEremopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFernEpiphyte.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGiantHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLepidophloiosSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLesleya.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLyginopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMacroneuropterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMarattia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMedullosalesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNemejcopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOdontopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOmphalophloiosSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPitysSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPsaroniusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockReticulopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRufloriaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSigillariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenophyllales.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenophyllales1.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockStauropteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSublepidodendron.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTmesipteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockValmeyerodendronSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWalchiaSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZygopteridaceaeSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString()
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsCarboniferous);
        return resLoc;
    }

    public static String[] getPermianCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAraucarioxylonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAridHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockArthropitysSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBaiera.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBelemnopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBjuviaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBrachyphyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBrasilodendron.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBuriadia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCladophlebis.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCordaitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCyclodendron.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEmplectopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEquisitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFernEpiphyte.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFieldHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGiantHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGigantopteridSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGlenopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGlossopterisSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemIsoetesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLepidopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLesleya.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMarattia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMedullosalesSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemNeocalamitesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNoeggerathialesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNystroemia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOrtiseia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPalaeognetaleana.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPalaeostachyaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPaurodendron.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemPhyllothecaItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPolyspermophyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPsaroniusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPseudovoltzia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockQuadrocladus.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockQuasistrobus.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRufloriaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSchizoneuraSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockScolecopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSigillariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSkaaripteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSurangephyllum.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemSwampHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockThucydia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTieteaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTmesipteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTrichopitys.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockUtrechtiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWachtlerina.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWalchiaSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWoodHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZygopteridaceaeSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZygopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPhasmatocycasSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString()
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsPermian);
        return resLoc;
    }

    public static String[] getTriassicCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAethophyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAlpiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAnomozamitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAntarcticycas.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAraucarioxylonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAridHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBaiera.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBjuviaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBrachyphyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCaytoniales.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCaytoniales2.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCladophlebis.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClathropteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClaytosmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockConiopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCtenisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCzekanowskiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicroidiumE.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicroidiumFSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicroidiumOSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicroidiumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDictyophyllum.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemEquisetitesReedItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEquisitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFernEpiphyte.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFieldHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFurculaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGiantHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockIschnophyton.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemIsoetesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLadiniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLepidopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLeptocycasSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLycopia.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemMacrotaeniopterisItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMarattia.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemNeocalamitesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNeuropteridium.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOtozamites.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPachypterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPetriellales.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemPhyllothecaItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPleuromeiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPodocarpSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPterophyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPtilophyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSahnioxylonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSanmiguelia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSchizoneuraSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSciadopitysSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockScytophyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSpaciinodum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenobaieraSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemSwampHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTelemachusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTmesipteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockToditesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTongchuanophyllum.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWoodHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZamitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicroidiumHSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWachtleria.block).toString(),
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsTriassic);
        return resLoc;
    }

    public static String[] getJurassicCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAnomozamitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAraucaritesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAridHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBaiera.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBrachyphyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBristleconeSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBunyaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBushyAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCaytoniales.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCaytoniales2.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCinnamonFern.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCladophlebis.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClathropteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClaytosmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockColumnarisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockConiopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCtenisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycadeoideaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycadopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycasSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCypressSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCzekanowskiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicksoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDictyophyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDioonSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemEquisetitesReedItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEquisitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFernEpiphyte.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFieldHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFrenelopsis.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFurcifolium.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGiantHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGlossophyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGrassyHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHermanophyton.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockIschnophyton.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemIsoetesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockJurassicHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatonia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatoniaLarge.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLeptopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLygodium.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMarattia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMonkeyPuzzleAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniocladusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOsmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOtozamites.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPachypteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPachypterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPentoxylalesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPhoenicopsisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPodocarpSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPterophyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPtilophyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRedwoodSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSahnioxylonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSanmiguelia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSchmeissneria.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSciadopitysSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSpaciinodum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenobaieraSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockStiffCycadSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemSwampHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTallAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTmesipteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockToditesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernBlackSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernSilverSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockUmaltolepis.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWilliamsoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWoodHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZamitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockKomlopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCunninghamiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCephalotaxusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHoopSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAmentotaxus.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockScrubbyPineSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterCloverItem.block).toString()
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsJurassic);
        return resLoc;
    }

    public static String[] getCretaceousCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAgathisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAnomozamitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockArchaeanthusSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemArchaefructusItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAridHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockArtocarpusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBaiera.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBaikalophyllum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBanksia1Sapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBanksia2Sapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBolbitis.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBrachyphyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBristleconeSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBunyaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBushyAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCaytoniales.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCaytoniales2.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCinnamonFern.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCladophlebis.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClathropteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClaytosmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemCobbaniaItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockColumnarisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockConiopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCtenisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycadeoideaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycadopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycasSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCypressSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCzekanowskiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicksoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDioonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEphedra.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemEquisetitesReedItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEquisitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFernEpiphyte.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFieldHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFrenelopsis.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGiantHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGlossophyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGrassyHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHermanophyton.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHironoiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockIschnophyton.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemIsoetesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatoniaLarge.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLeptopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLiriodendronSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLygodium.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMagnoliaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMapleSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMarattia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMonkeyPuzzleAraucariaSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemNathorstianaItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNelumbo.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniocladusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNilssoniopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNothofagusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOsmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOtozamites.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPachypteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPachypterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPentoxylalesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPhoenicopsisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPlaneSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPodocarpSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemPrimevalGrassItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProteaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProteaSapling1.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPterophyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPtilophyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRedwoodSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSahnioxylonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSciadopitysSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatonia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSpaciinodum.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenobaieraSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSphenopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockStiffCycadSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemSwampHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSycamoreSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTaxodiumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTempskyaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTmesipteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockToditesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernBlackSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernSilverSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTyrmia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockUmaltolepis.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWielandiella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWilliamsoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWollemiSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWoodHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockYewSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZamitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockKomlopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCunninghamiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCephalotaxusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHoopSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAmentotaxus.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockScrubbyPineSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterCloverItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAcrocomiaSapling.block).toString(),
                "minecraft:oak_sapling",
                "minecraft:dark_oak_sapling",
                "minecraft:spruce_sapling"
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsCretaceous);
        return resLoc;
    }

    public static String[] getPaleogeneCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAgathisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAridHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockArtocarpusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBanksia1Sapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBanksia2Sapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBeechSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBrachyphyllumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBristleconeSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBunyaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBushyAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCinnamonFern.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClaytosmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockColumnarisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCtenisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycasSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCypressSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicksoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDioonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEphedra.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFernEpiphyte.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFieldHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGiantHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGrassyHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHymenaeaSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemIsoetesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatoniaLarge.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLeptopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLiriodendronSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLygodium.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMagnoliaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMapleSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMarattia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMonkeyPuzzleAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNelumbo.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNothofagusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOsmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPlaneSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPodocarpSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemPrimevalGrassItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProteaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProteaSapling1.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRedwoodSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSciadopitysSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatonia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockStiffCycadSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemSwampHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSycamoreSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTaxodiumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTmesipteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernBlackSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernSilverSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWollemiSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWoodHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockYewSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockZamitesSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockKomlopterisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCunninghamiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCephalotaxusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHoopSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAmentotaxus.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockScrubbyPineSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterCloverItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAcrocomiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPhoenixSapling.block).toString(),
                "minecraft:oak_sapling",
                "minecraft:acacia_sapling",
                "minecraft:dark_oak_sapling",
                "minecraft:jungle_sapling",
                "minecraft:birch_sapling",
                "minecraft:spruce_sapling"
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsPaleogene);
        return resLoc;
    }

    public static String[] getNeogeneCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAgathisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAppleSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAridHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockArtocarpusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBanksia1Sapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBanksia2Sapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBeechSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBristleconeSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBunyaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBushyAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCinnamonFern.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClaytosmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockColumnarisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycasSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCypressSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicksoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDioonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEncblueSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEphedra.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFernEpiphyte.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFieldHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGiantHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGrassyHorsetail.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemIsoetesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatoniaLarge.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLeptopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLiriodendronSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLygodium.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMagnoliaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMapleSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMarattia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMonkeyPuzzleAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNelumbo.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNothofagusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOsmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPlaneSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPodocarpSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProteaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProteaSapling1.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRedwoodSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSciadopitysSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatonia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSpinyCycadSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockStiffCycadSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemSwampHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSycamoreSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTaxodiumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTmesipteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernBlackSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernSilverSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWollemiSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWoodHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockYewSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCunninghamiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCephalotaxusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHoopSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAmentotaxus.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockScrubbyPineSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterCloverItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAcrocomiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPhoenixSapling.block).toString(),
                "minecraft:oak_sapling",
                "minecraft:acacia_sapling",
                "minecraft:dark_oak_sapling",
                "minecraft:jungle_sapling",
                "minecraft:birch_sapling",
                "minecraft:spruce_sapling"
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsNeogene);
        return resLoc;
    }

    public static String[] getPleistoceneCleanedFossilsPlants() {
        String[] resLoc = {
                ForgeRegistries.BLOCKS.getKey(BlockAgathisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAppleSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAridHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockArtocarpusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBanksia1Sapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBanksia2Sapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBeechSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBristleconeSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBunyaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockBushyAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCinnamonFern.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClaytosmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockClubmoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockColumnarisSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCycasSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCypressSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDicksoniaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockDioonSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEncblueSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockEphedra.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFernEpiphyte.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockFieldHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGiantHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGinkgoSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockGrassyHorsetail.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemIsoetesItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatoniaLarge.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLeptopteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLiriodendronSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockLygodium.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMagnoliaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMapleSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMarattia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMonkeyPuzzleAraucariaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNelumbo.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockNothofagusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockOsmunda.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPlaneSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPodocarpSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProteaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockProteaSapling1.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockRedwoodSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSciadopitysSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSelaginella.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockMatonia.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSpinyCycadSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockStiffCycadSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemSwampHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockSycamoreSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTaxodiumSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTmesipteris.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernBlackSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockTreefernSilverSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterHorsetailItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWollemiSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockWoodHorsetail.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockYewSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCunninghamiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockCephalotaxusSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockHoopSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAncientMoss.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAmentotaxus.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockScrubbyPineSapling.block).toString(),
                ForgeRegistries.ITEMS.getKey(ItemWaterCloverItem.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockAcrocomiaSapling.block).toString(),
                ForgeRegistries.BLOCKS.getKey(BlockPhoenixSapling.block).toString(),
                "minecraft:oak_sapling",
                "minecraft:acacia_sapling",
                "minecraft:dark_oak_sapling",
                "minecraft:jungle_sapling",
                "minecraft:birch_sapling",
                "minecraft:spruce_sapling"
        };
        resLoc = ArrayUtils.addAll(resLoc, LepidodendronConfig.revPlantsPleistocene);
        return resLoc;
    }

}
