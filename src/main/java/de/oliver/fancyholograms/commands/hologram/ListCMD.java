package de.oliver.fancyholograms.commands.hologram;

import de.oliver.fancyholograms.FancyHolograms;
import de.oliver.fancyholograms.api.Hologram;
import de.oliver.fancyholograms.commands.Subcommand;
import de.oliver.fancyholograms.util.Constants;
import de.oliver.fancylib.MessageHelper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ListCMD implements Subcommand {

    @Override
    public List<String> tabcompletion(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        return null;
    }

    @Override
    public boolean run(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        final var holograms = FancyHolograms.get().getHologramsManager().getHolograms();

        if (holograms.isEmpty()) {
            MessageHelper.warning(player, "There are no holograms. Use '/hologram create' to create one");
        } else {
            MessageHelper.info(player, "<b>List of all holograms:</b>");

            for (final var holo : holograms) {
                final var location = holo.getData().getLocation();
                if (location == null || location.getWorld() == null) {
                    continue;
                }

                MessageHelper.info(player,
                        "<hover:show_text:'<gray><i>Click to teleport</i></gray>'><click:run_command:'%s'> - %s (%s/%s/%s)</click></hover>"
                                .formatted("/hologram teleport " + holo.getData().getName(),
                                        holo.getData().getName(),
                                        Constants.DECIMAL_FORMAT.format(location.x()),
                                        Constants.DECIMAL_FORMAT.format(location.y()),
                                        Constants.DECIMAL_FORMAT.format(location.z())));
            }
        }

        return true;
    }
}