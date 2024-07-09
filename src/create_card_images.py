from PIL import Image, ImageDraw, ImageFont
import os

# Define card properties
suits = ['hearts', 'diamonds', 'clubs', 'spades']
ranks = ['ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'jack', 'queen', 'king']
card_size = (100, 150)
output_folder = 'images'

# Create output folder if it doesn't exist
os.makedirs(output_folder, exist_ok=True)

# Function to create a card image
def create_card_image(rank, suit, filename):
    # Create a white image
    image = Image.new('RGB', card_size, color='white')
    draw = ImageDraw.Draw(image)
    
    # Draw a border
    draw.rectangle([0, 0, card_size[0]-1, card_size[1]-1], outline='black')
    
    # Add text
    font = ImageFont.load_default()
    text = f"{rank} of {suit}"
    text_width = draw.textlength(text, font=font)
    text_height = font.size
    text_position = ((card_size[0] - text_width) // 2, (card_size[1] - text_height) // 2)
    draw.text(text_position, text, fill='black', font=font)
    
    # Save the image
    image.save(os.path.join(output_folder, filename))

# Create card images
for suit in suits:
    for rank in ranks:
        filename = f"{rank}_of_{suit}.png"
        create_card_image(rank, suit, filename)

# Create card back
create_card_image("", "", "card_back.png")

print(f"Created {len(suits) * len(ranks) + 1} card images in the '{output_folder}' folder.")