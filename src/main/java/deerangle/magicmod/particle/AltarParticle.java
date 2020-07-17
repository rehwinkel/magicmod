package deerangle.magicmod.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

import javax.annotation.Nullable;

public class AltarParticle extends SpriteTexturedParticle {

    protected AltarParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        this.particleRed = 0.0F;
        super.renderParticle(buffer, renderInfo, partialTicks);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private IAnimatedSprite sprite;

        public Factory(IAnimatedSprite sprite) {
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            AltarParticle particle = new AltarParticle(worldIn, x, y, z);
            particle.selectSpriteRandomly(this.sprite);
            return particle;
        }
    }
}
