package gregtech.api.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;

import org.jetbrains.annotations.NotNull;

/**
 * Shared environmental helpers for Nether geothermal machines.
 */
public final class GeothermalUtils {

    private GeothermalUtils() {}

    public static boolean isNetherDimension(@NotNull World world) {
        return isNetherProvider(world.provider);
    }

    public static boolean isNetherProvider(@NotNull WorldProvider provider) {
        return provider.isNether() || provider instanceof WorldProviderHell || provider.doesWaterVaporize();
    }

    public static double getYLevelMultiplier(@NotNull World world, @NotNull BlockPos pos) {
        int y = pos.getY();
        if (isNetherDimension(world)) {
            if (y <= 10) return 1.5;
            if (y <= 31) return 1.3;
            if (y <= 80) return 1.1;
            return 1.0;
        }

        if (y <= 16) return 1.2;
        if (y <= 63) return 1.0;
        return 0.8;
    }

    public static double getDimensionMultiplier(@NotNull World world) {
        return isNetherDimension(world) ? 1.5 : 1.0;
    }

    public static double getGeothermalMultiplier(@NotNull World world, @NotNull BlockPos pos) {
        return getDimensionMultiplier(world) * getYLevelMultiplier(world, pos);
    }
}
