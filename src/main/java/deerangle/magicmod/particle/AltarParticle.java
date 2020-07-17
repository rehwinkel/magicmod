package deerangle.magicmod.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class AltarParticle extends SpriteTexturedParticle {

    private final double targetX, targetY, targetZ;

    protected AltarParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, double targetX, double targetY, double targetZ) {
        super(world, x + (0.2 * Math.random()) - 0.1, y, z + (0.2 * Math.random()) - 0.1);
        float len = MathHelper.sqrt(velX * velX + velY * velY + velZ * velZ);
        this.motionX = velX / (double) len * 0.4 + (0.1 * Math.random());
        this.motionY = velY / (double) len * 0.4 + (0.1 * Math.random());
        this.motionZ = velZ / (double) len * 0.4 + (0.1 * Math.random());
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.maxAge = 1000;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        Vector3d target = new Vector3d(this.targetX, this.targetY, this.targetZ);
        Vector3d pos = new Vector3d(this.posX, this.posY, this.posZ);
        if ((this.targetX - this.posX) * (this.targetX - this.posX) + (this.targetY - this.posY) * (this.targetY - this.posY) + (this.targetZ - this.posZ) * (this.targetZ - this.posZ) < 0.05) {
            this.setExpired();
        } else {
            Vector3d toTarget = target.subtract(pos).normalize().mul(0.3, 0.3, 0.3);
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            this.posX += toTarget.getX();
            this.posY += toTarget.getY();
            this.posZ += toTarget.getZ();
            this.motionX *= 0.8;
            this.motionY *= 0.8;
            this.motionZ *= 0.8;
            /*
            this.motionY -= 0.04D * (double) this.particleGravity;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double) 0.98F;
            this.motionY *= (double) 0.98F;
            this.motionZ *= (double) 0.98F;
            if (this.onGround) {
                this.motionX *= (double) 0.7F;
                this.motionZ *= (double) 0.7F;
            }
            */
        }
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        super.renderParticle(buffer, renderInfo, partialTicks);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements IParticleFactory<AltarParticleData> {
        private IAnimatedSprite sprite;

        public Factory(IAnimatedSprite sprite) {
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle makeParticle(AltarParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            AltarParticle particle = new AltarParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, typeIn.getTargetX(),
                    typeIn.getTargetY(), typeIn.getTargetZ());
            particle.selectSpriteRandomly(this.sprite);
            return particle;
        }
    }
}
