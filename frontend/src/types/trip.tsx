import {z} from 'zod';

const Trip = z.object({
    id: z.string(),
    name: z.string(),
    destination: z.string(),
    coverPhotoUrl: z.string(),
    startDate: z.string(),
    endDate: z.string(),
    userId: z.string(),
}); 

export type Trip = z.infer<typeof Trip>;