package net.cubealive.cubealivemod.entity.girlfriend;

import com.google.common.collect.Maps;
import net.cubealive.cubealivemod.CubealiveMod;
import net.cubealive.cubealivemod.client.ClientHandler;
import net.cubealive.cubealivemod.gui.girlfriend.GirlfriendGUIMain;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

public class GirlfriendEntity extends TameableEntity {
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
    protected boolean sitting = false;

    //Make Girlfriends skin like a cat

    private static final DataParameter<Integer> GIRLFRIEND_TYPE = EntityDataManager.createKey(GirlfriendEntity.class, DataSerializers.VARINT);
    public static final Map<Integer, ResourceLocation> girlfriend_skins = Util.make(Maps.newHashMap(), (x) -> {
        x.put(0, new ResourceLocation(CubealiveMod.MODID,"textures/entity/girlfriend/girlfriend.png"));
        x.put(1, new ResourceLocation(CubealiveMod.MODID,"textures/entity/girlfriend/girlfriend2.png"));
    });

    public GirlfriendEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
        //jumpController = new JumpHelperController(this);
        //moveController = new MoveHelperController(this);
        //setMovementSpeed(0.0D);
    }
    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(10, new FollowOwnerGoal(this,0.5,2,20,true));
        //this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        //applyEntityAI();
    }

    public int getGirlfriendType() {
        return this.dataManager.get(GIRLFRIEND_TYPE);
    }

    public ResourceLocation getGirlfriendTypeName() {
        return girlfriend_skins.getOrDefault(this.getGirlfriendType(), girlfriend_skins.get(0));
    }

    public void setGirlfriendType(int x) {
        if (x < 0 || x >= 2) {
            x = this.rand.nextInt(10-8);
        }

        this.dataManager.set(GIRLFRIEND_TYPE, x);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("GirlfriendType", this.getGirlfriendType());
        //compound.putByte("CollarColor", (byte)this.getCollarColor().getId());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setGirlfriendType(compound.getInt("GirlfriendType"));
        //if (compound.contains("CollarColor", 99)) {
        //    this.setCollarColor(DyeColor.byId(compound.getInt("CollarColor")));
        //}
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(GIRLFRIEND_TYPE, 1);
    }


    protected void applyEntityAI() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        //this.goalSelector.addGoal(2, new WabbitAttackGoal(Wabbit, 1.5D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(GirlfriendEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, RabbitEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        if(player.getHeldItem(hand).getItem() == Items.DIAMOND){
            this.setTamedBy(player);
            this.setCustomName(new StringTextComponent(player.getScoreboardName()+"'s Girlfriend"));
        }
        if(world.isRemote){
            if(hand == Hand.MAIN_HAND){
                if(player.getHeldItemMainhand().isEmpty()){
                    ClientHandler.openGirlfriendGUI(player,this);
                }
            }
        }

        return super.processInteract(player, hand);
    }


    @Override
    public void swingArm(Hand hand) {
        super.swingArm(hand);
    }

    public void sit(){
        if(this.isSitting()){
            this.setSitting(false);
        } else {
            this.setSitting(true);
        }
    }
}