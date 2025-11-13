package net.succ.succsmod.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.custom.*;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.worldgen.tree.ModTreeGrowers;


import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SuccsMod.MOD_ID);

    public static final DeferredBlock<Block> ATHERIUM_ORE = registerBlock("atherium_ore",
            () -> new DropExperienceBlock(UniformInt.of(4,8),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> DEEPSLATE_ATHERIUM_ORE = registerBlock("deepslate_atherium_ore",
            () -> new DropExperienceBlock(UniformInt.of(4,8),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> NETHER_ATHERIUM_ORE = registerBlock("nether_atherium_ore",
            () -> new DropExperienceBlock(UniformInt.of(4,8),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> END_ATHERIUM_ORE = registerBlock("end_atherium_ore",
            () -> new DropExperienceBlock(UniformInt.of(4,8),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> ATHERIUM_BLOCK = registerBlock("atherium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> MALACHITE_ORE = registerBlock("malachite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DEEPSLATE_MALACHITE_ORE = registerBlock("deepslate_malachite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHER_MALACHITE_ORE = registerBlock("nether_malachite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> END_MALACHITE_ORE = registerBlock("end_malachite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> MALACHITE_BLOCK = registerBlock("malachite_block",
            () -> new Block (BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHER_RUBY_ORE = registerBlock("nether_ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> END_RUBY_ORE = registerBlock("end_ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHER_SAPPHIRE_ORE = registerBlock("nether_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> END_SAPPHIRE_ORE = registerBlock("end_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SUNSTONE_ORE = registerBlock("sunstone_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DEEPSLATE_SUNSTONE_ORE = registerBlock("deepslate_sunstone_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHER_SUNSTONE_ORE = registerBlock("nether_sunstone_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> END_SUNSTONE_ORE = registerBlock("end_sunstone_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SUNSTONE_BLOCK = registerBlock("sunstone_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> JASPILITE_ORE = registerBlock("jaspilite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DEEPSLATE_JASPILITE_ORE = registerBlock("deepslate_jaspilite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHER_JASPILITE_ORE = registerBlock("nether_jaspilite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> END_JASPILITE_ORE = registerBlock("end_jaspilite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> JASPILITE_BLOCK = registerBlock("jaspilite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GARLIC_CROP = BLOCKS.register("garlic_crop",
            () -> new GarlicCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS).noCollission().noOcclusion()));

    public static final DeferredBlock<Block> GEM_POLISHING_TABLE = registerBlock("gem_polishing_table",
            () -> new GemPolishingTableBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SHATTERBLOOM_DOOR = registerBlock("shatterbloom_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).strength(3f).noOcclusion()));

    public static final DeferredBlock<Block> SHATTERBLOOM_TRAPDOOR = registerBlock("shatterbloom_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).strength(3f).noOcclusion()));

    public static final DeferredBlock<Block> SHATTERBLOOM_LOG = registerBlock("shatterbloom_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<Block> SHATTERBLOOM_WOOD = registerBlock("shatterbloom_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<Block> STRIPPED_SHATTERBLOOM_LOG = registerBlock("stripped_shatterbloom_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final DeferredBlock<Block> STRIPPED_SHATTERBLOOM_WOOD = registerBlock("stripped_shatterbloom_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final DeferredBlock<Block> SHATTERBLOOM_PLANKS = registerBlock("shatterbloom_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final DeferredBlock<Block> SHATTERBLOOM_LEAVES = registerBlock("shatterbloom_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredBlock<Block> SHATTERBLOOM_STAIRS = registerBlock("shatterbloom_stairs",
            () -> new StairBlock(ModBlocks.SHATTERBLOOM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));

    public static final DeferredBlock<Block> SHATTERBLOOM_SLAB = registerBlock("shatterbloom_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

    public static final DeferredBlock<Block> SHATTERBLOOM_SAPLING = registerBlock("shatterbloom_sapling",
            () -> new SaplingBlock(ModTreeGrowers.SHATTERBLOOM, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> SHATTERBLOOM_PRESSURE_PLATE = registerBlock("shatterbloom_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> SHATTERBLOOM_BUTTON = registerBlock("shatterbloom_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).noCollission()));

    public static final DeferredBlock<Block> SHATTERBLOOM_FENCE = registerBlock("shatterbloom_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<Block> SHATTERBLOOM_FENCE_GATE = registerBlock("shatterbloom_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_LOG = registerBlock("mycelial_sporewood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HYPHAE)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_WOOD = registerBlock("mycelial_sporewood_wood",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STEM)));

    public static final DeferredBlock<Block> STRIPPED_MYCELIAL_SPOREWOOD_LOG = registerBlock("stripped_mycelial_sporewood_log",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CRIMSON_HYPHAE)));

    public static final DeferredBlock<Block> STRIPPED_MYCELIAL_SPOREWOOD_WOOD = registerBlock("stripped_mycelial_sporewood_wood",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CRIMSON_STEM)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_PLANKS = registerBlock("mycelial_sporewood_planks",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)){
                    @Override
                    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                        return true;
                    }

                    @Override
                    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                        return 20;
                    }

                    @Override
                    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                        return 5;
                    }
                });

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_STAIRS = registerBlock("mycelial_sporewood_stairs",
                () -> new StairBlock(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get().defaultBlockState(),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STAIRS)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_SLAB = registerBlock("mycelial_sporewood_slab",
                () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_PRESSURE_PLATE = registerBlock("mycelial_sporewood_pressure_plate",
                () -> new PressurePlateBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_BUTTON = registerBlock("mycelial_sporewood_button",
                () -> new ButtonBlock(BlockSetType.CRIMSON, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_BUTTON).noCollission()));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_FENCE = registerBlock("mycelial_sporewood_fence",
                () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_FENCE_GATE = registerBlock("mycelial_sporewood_fence_gate",
                () -> new FenceGateBlock(WoodType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE_GATE)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_DOOR = registerBlock("mycelial_sporewood_door",
                () -> new DoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_DOOR).strength(3f).noOcclusion()));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_TRAPDOOR = registerBlock("mycelial_sporewood_trapdoor",
                () -> new TrapDoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR).strength(3f).noOcclusion()));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_SAPLING = registerBlock("mycelial_sporewood_sapling",
            () -> new SaplingBlock(ModTreeGrowers.MYCELIAL_SPOREWOOD_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_ROOTS)));

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_LEAVES = registerBlock("mycelial_sporewood_leaves",
            GlowingLeavesBlock::new);

    public static final DeferredBlock<Block> MYCELIAL_SPOREWOOD_VINE = registerBlock("mycelial_sporewood_vine",
            () -> new ModVineBlock());

    public static final DeferredBlock<Block> CRYOHEART_LOG = registerBlock("cryoheart_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<Block> CRYOHEART_WOOD = registerBlock("cryoheart_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<Block> STRIPPED_CRYOHEART_LOG = registerBlock("stripped_cryoheart_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final DeferredBlock<Block> STRIPPED_CRYOHEART_WOOD = registerBlock("stripped_cryoheart_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final DeferredBlock<Block> CRYOHEART_PLANKS = registerBlock("cryoheart_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final DeferredBlock<Block> CRYOHEART_STAIRS = registerBlock("cryoheart_stairs",
            () -> new StairBlock(ModBlocks.CRYOHEART_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));

    public static final DeferredBlock<Block> CRYOHEART_SLAB = registerBlock("cryoheart_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<Block> CRYOHEART_PRESSURE_PLATE = registerBlock("cryoheart_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> CRYOHEART_BUTTON = registerBlock("cryoheart_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).noCollission()));
    public static final DeferredBlock<Block> CRYOHEART_FENCE = registerBlock("cryoheart_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<Block> CRYOHEART_FENCE_GATE = registerBlock("cryoheart_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<Block> CRYOHEART_DOOR = registerBlock("cryoheart_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).strength(3f).noOcclusion()));
    public static final DeferredBlock<Block> CRYOHEART_TRAPDOOR = registerBlock("cryoheart_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).strength(3f).noOcclusion()));
    public static final DeferredBlock<Block> CRYOHEART_SAPLING = registerBlock("cryoheart_sapling",
            () -> new SaplingBlock(ModTreeGrowers.CRYOHEART, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<Block> CRYOHEART_LEAVES = registerBlock("cryoheart_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredBlock<Block> EMBERPINE_LOG = registerBlock("emberpine_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HYPHAE)));

    public static final DeferredBlock<Block> EMBERPINE_WOOD = registerBlock("emberpine_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HYPHAE)));
    public static final DeferredBlock<Block> STRIPPED_EMBERPINE_LOG = registerBlock("stripped_emberpine_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HYPHAE)));
    public static final DeferredBlock<Block> STRIPPED_EMBERPINE_WOOD = registerBlock("stripped_emberpine_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HYPHAE)));

    public static final DeferredBlock<Block> EMBERPINE_PLANKS = registerBlock("emberpine_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final DeferredBlock<Block> EMBERPINE_STAIRS = registerBlock("emberpine_stairs",
            () -> new StairBlock(ModBlocks.EMBERPINE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STAIRS)));

    public static final DeferredBlock<Block> EMBERPINE_SLAB = registerBlock("emberpine_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB)));

    public static final DeferredBlock<Block> EMBERPINE_SAPLING = registerBlock("emberpine_sapling",
            () -> new EmberpineSaplingBlock(
                    ModTreeGrowers.EMBERPINE,                     // your TreeGrower
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
            ));


    public static final DeferredBlock<Block> EMBERPINE_LEAVES = registerBlock("emberpine_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });

    public static final DeferredBlock<Block> EMBERPINE_PRESSURE_PLATE = registerBlock("emberpine_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.CRIMSON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE)));

    public static final DeferredBlock<Block> EMBERPINE_BUTTON = registerBlock("emberpine_button",
            () -> new ButtonBlock(BlockSetType.CRIMSON, 10,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_BUTTON).noCollission()));

    public static final DeferredBlock<Block> EMBERPINE_FENCE = registerBlock("emberpine_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE)));

    public static final DeferredBlock<Block> EMBERPINE_FENCE_GATE = registerBlock("emberpine_fence_gate",
            () -> new FenceGateBlock(WoodType.CRIMSON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE_GATE)));

    public static final DeferredBlock<Block> EMBERPINE_DOOR = registerBlock("emberpine_door",
            () -> new DoorBlock(BlockSetType.CRIMSON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_DOOR).strength(3f).noOcclusion()));

    public static final DeferredBlock<Block> EMBERPINE_TRAPDOOR = registerBlock("emberpine_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.CRIMSON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR).strength(3f).noOcclusion()));

    // Glowcap woodset
    public static final DeferredBlock<Block> GLOWCAP_STEM = registerBlock("glowcap_stem",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STEM).sound(SoundType.STEM)));

    public static final DeferredBlock<Block> GLOWCAP_HYPHAE = registerBlock("glowcap_hyphae",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HYPHAE).sound(SoundType.STEM)));

    public static final DeferredBlock<Block> STRIPPED_GLOWCAP_STEM = registerBlock("stripped_glowcap_stem",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CRIMSON_STEM).sound(SoundType.STEM)));

    public static final DeferredBlock<Block> STRIPPED_GLOWCAP_HYPHAE = registerBlock("stripped_glowcap_hyphae",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CRIMSON_HYPHAE).sound(SoundType.STEM)));

    public static final DeferredBlock<Block> GLOWCAP_PLANKS = registerBlock("glowcap_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> GLOWCAP_STAIRS = registerBlock("glowcap_stairs",
            () -> new StairBlock(ModBlocks.GLOWCAP_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STAIRS)));

    public static final DeferredBlock<Block> GLOWCAP_SLAB = registerBlock("glowcap_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB)));

    public static final DeferredBlock<Block> GLOWCAP_PRESSURE_PLATE = registerBlock("glowcap_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.CRIMSON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE)));

    public static final DeferredBlock<Block> GLOWCAP_BUTTON = registerBlock("glowcap_button",
            () -> new ButtonBlock(BlockSetType.CRIMSON, 10,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_BUTTON).noCollission()));

    public static final DeferredBlock<Block> GLOWCAP_FENCE = registerBlock("glowcap_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE)));

    public static final DeferredBlock<Block> GLOWCAP_FENCE_GATE = registerBlock("glowcap_fence_gate",
            () -> new FenceGateBlock(WoodType.CRIMSON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE_GATE)));

    public static final DeferredBlock<Block> GLOWCAP_DOOR = registerBlock("glowcap_door",
            () -> new DoorBlock(BlockSetType.CRIMSON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_DOOR).strength(3f).noOcclusion()));

    public static final DeferredBlock<Block> GLOWCAP_TRAPDOOR = registerBlock("glowcap_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.CRIMSON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR).strength(3f).noOcclusion()));




    public static final DeferredBlock<Block> POISON_LILY = registerBlock("poison_lily",
            () -> new PoisonLilyBlock(
                    MobEffects.POISON,
                    40,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).noCollission().lightLevel(state -> 3)
            ));

    public static final DeferredBlock<Block> POTTED_POISON_LILY = registerBlock("potted_poison_lily",
            () -> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT), POISON_LILY, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)));
    public static final DeferredBlock<Block> POTTED_SHATTERBLOOM_SAPLING = registerBlock("potted_shatterbloom_sapling",
            () -> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT), SHATTERBLOOM_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)));
    public static final DeferredBlock<Block> POTTED_MYCELIAL_SPOREWOOD_SAPLING = registerBlock("potted_mycelial_sporewood_sapling",
            () -> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT), MYCELIAL_SPOREWOOD_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_CRIMSON_ROOTS)));
    public static final DeferredBlock<Block> POTTED_CRYOHEART_SAPLING = registerBlock("potted_cryoheart_sapling",
            () -> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT), CRYOHEART_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)));
    public static final DeferredBlock<Block> POTTED_EMBERPINE_SAPLING = registerBlock("potted_emberpine_sapling",
            () -> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT), EMBERPINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)));


    public static final DeferredBlock<Block> SCORCHED_SAND = registerBlock("scorched_sand",
            () -> new ScorchedSandBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(0.5F)
                            .sound(SoundType.SAND)
            ));

    public static final DeferredBlock<Block> CRIMSON_MYCELIUM = registerBlock("crimson_mycelium",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
