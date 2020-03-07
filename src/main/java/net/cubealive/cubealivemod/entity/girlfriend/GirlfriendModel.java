package net.cubealive.cubealivemod.entity.girlfriend;

/*
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.girlfriend.ModelRenderer;
import net.cubealive.cubealivemod.entity.WabbitEntity;
import net.minecraft.client.renderer.entity.girlfriend.EntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WabbitModel<T extends WabbitEntity> extends EntityModel<T> {
    public ModelRenderer bullet;
    public ModelRenderer bullet2;


    public WabbitModel()
    {
        textureWidth = 8;
        textureHeight = 4;
        bullet = new ModelRenderer(this, 0, 0);
        bullet.setRotationPoint(0.0F, 0.0F, 0.0F);
        bullet.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10);
        bullet2 = new ModelRenderer(this, 0, 0);
        bullet2.setRotationPoint(0.0F, 0.0F, 0.0F);
        bullet2.addBox(5.0F, 5.0F, 5.0F, 10, 20, 20);
    }


    @Override
    public void render(MatrixStack matrix, IVertexBuilder builder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        bullet.render(matrix, builder, packedLight, packedOverlay, red, green, blue, alpha);
        bullet2.render(matrix, builder, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setRotationAngles(WabbitEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}
*/
//Made with Blockbench
//Paste this code into your mod.

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.MathHelper;

public class GirlfriendModel<W extends TameableEntity> extends EntityModel<GirlfriendEntity> {
    private final ModelRenderer leg_left;
    private final ModelRenderer leg_right;
    private final ModelRenderer torso;
    private final ModelRenderer arm_left;
    private final ModelRenderer arm_right;
    private final ModelRenderer head;
    private final ModelRenderer boob;

    private final ModelRenderer hat;

    float head_const = 0.012453292F;

    /*
    private final ModelRenderer hat_right;
    private final ModelRenderer hat_front;
    private final ModelRenderer hat_left;
    private final ModelRenderer hat_back;
    private final ModelRenderer hat_top;
    private final ModelRenderer hat_bottom;
    */

    public GirlfriendModel() {
        textureWidth = 64;
        textureHeight = 64;

        leg_left = new ModelRenderer(this,16,48);
        leg_right = new ModelRenderer(this,0,16);
        torso = new ModelRenderer(this,16,16);
        arm_left = new ModelRenderer(this,40,16);
        arm_right = new ModelRenderer(this,32,48);
        head = new ModelRenderer(this,0,0);
        boob = new ModelRenderer(this,16,16);

        hat = new ModelRenderer(this,32,0);

        //Hat Textures
        /*
        hat_right = new ModelRenderer(this,32,0);
        hat_front = new ModelRenderer(this,0,0);
        hat_left = new ModelRenderer(this,0,0);
        hat_back = new ModelRenderer(this,0,0);
        hat_top = new ModelRenderer(this,0,0);
        hat_bottom = new ModelRenderer(this,0,0);
        */
        //bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        //bb_main.addBox(5,10,0,4,12,4);
        //bb_main.addBox(5,10,-4,4,12,4);

        int offset_x = -2;
        float offset_y = 1.9F;
        int offset_z = -2;

        //Legs
        leg_left.addBox( 2+offset_x, 10+offset_y, -2+offset_z, 4, 12, 4 );
        leg_right.addBox( -2+offset_x, 10+offset_y, -2+offset_z, 4, 12, 4 );
        //Torso
        torso.addBox( -2+offset_x, -2+offset_y, -2+offset_z, 8, 12, 4 );
        //Arms
        arm_left.addBox( 6+offset_x, -2+offset_y, -2+offset_z, 3, 12, 4 );
        arm_right.addBox( -5+offset_x, -2+offset_y, -2+offset_z, 3, 12, 4 );
        //Boobs
        boob.addBox( -2+offset_x, -1+offset_y, -3+offset_z, 8, 4, 4 );
        //Head
        head.addBox( -2+offset_x, -10+offset_y, -5+offset_z, 8, 8, 8 );
        //Hat
        hat.addBox( -2+offset_x, -10+offset_y, -5+offset_z, 8, 8, 8 );

        // -- ROTATION ---

        //-------------------


        //Hat Models
        //hat_right.addBox(-10,0,-10,0,8,8);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(GirlfriendEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        leg_left.rotateAngleX = -MathHelper.cos(limbSwing*0.662F)*(float)Math.PI*0.1F*limbSwingAmount;
        leg_right.rotateAngleX = MathHelper.cos(limbSwing*0.662F)*(float)Math.PI*0.1F*limbSwingAmount;

        arm_left.rotateAngleX = MathHelper.cos(limbSwing*0.662F)*(float)Math.PI*0.1F*limbSwingAmount;
        arm_right.rotateAngleX = -MathHelper.cos(limbSwing*0.662F)*(float)Math.PI*0.1F*limbSwingAmount;

        head.rotateAngleY = netHeadYaw * head_const;
        head.rotateAngleX = headPitch * head_const;
        hat.rotateAngleY = netHeadYaw * head_const;
        hat.rotateAngleX = headPitch * head_const;
    }

    //@Override
   //public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    //    bb_main.render(f5);
    //}
    //public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    //    modelRenderer.rotateAngleX = x;
    //    modelRenderer.rotateAngleY = y;
    //    modelRenderer.rotateAngleZ = z;
    //}



    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        leg_left.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leg_right.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        arm_left.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        arm_right.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        boob.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

        hat.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}